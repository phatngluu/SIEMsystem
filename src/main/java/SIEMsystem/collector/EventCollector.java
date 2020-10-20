package SIEMsystem.collector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import com.espertech.esper.runtime.client.EPRuntime;

import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.TcpPacketIncomingEvent;
import nl.basjes.parse.core.Parser;
import nl.basjes.parse.core.exceptions.DissectionFailure;
import nl.basjes.parse.core.exceptions.InvalidDissectorException;
import nl.basjes.parse.core.exceptions.MissingDissectorsException;
import nl.basjes.parse.httpdlog.HttpdLoglineParser;

public class EventCollector {
    private EPRuntime runtime;
    private static boolean add;

    public EventCollector(EPRuntime runtime) {
        this.runtime = runtime;
    }

    public void collectLog() throws FileNotFoundException {
        int numberoflog = 0;
        while (true) {
            ArrayList<AccessLogEvent> event = null;
            try {
                event = getEvent();
            } catch (IOException e) {
                e.printStackTrace();
            }
            int currnumberoflog = event.size();
            if (numberoflog < currnumberoflog) {
                for (int i = numberoflog; i < currnumberoflog; i++) {
                    this.runtime.getEventService().sendEventBean(event.get(i), "AccessLogEvent");
                }
                numberoflog = currnumberoflog;
            }
        }
    }

    public static ArrayList<AccessLogEvent> getEvent() throws IOException {
        ArrayList<AccessLogEvent> rs = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("/var/log/apache2/access.log"));
        String line = null;
        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLogEvent> dummyParser = new HttpdLoglineParser<AccessLogEvent>(AccessLogEvent.class, logformat);
        while ((line = reader.readLine()) != null) {
            AccessLogEvent logObj;
            try {
                logObj = dummyParser.parse(line);
                rs.add(logObj);
            } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                System.out.println("Failed to parse.");
            }
        }
        return rs;
    }

    public void collectAccessLog() {
        // while true
        // if new access log
        // parse & send event bean
        File file = new File(".resources/logs/input.log");

        String logformat = "%h %l %u %t \"%r\" %>s %b \"%{Referer}i\" \"%{User-Agent}i\"";
        Parser<AccessLogEvent> dummyParser = new HttpdLoglineParser<AccessLogEvent>(AccessLogEvent.class, logformat);

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String log = scanner.nextLine();
                AccessLogEvent logObj;
                try {
                    logObj = dummyParser.parse(log);
                    System.out.println(logObj);

                    // Send event bean
                    runtime.getEventService().sendEventBean(logObj, "AccessLogEvent");
                } catch (DissectionFailure | InvalidDissectorException | MissingDissectorsException e) {
                    // e.printStackTrace();
                    System.out.println("Failed to parse.");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to open file.");
        }
    }

    public void collectPacket() throws PcapNativeException, NotOpenException {
        String filter = "tcp"; // Filter TCP packets
        int PACKET_COUNT = -1; // Infinite
        int READ_TIMEOUT = 100; // Milisec
        int SNAPLEN = 65536; // Bytes
        String excludePorts = CEPEngine.getCreatedInstance().getProperty("PORTSCAN_EXCLUDE_PORTS"); 

        PcapNetworkInterface nif;
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.103");
            nif = Pcaps.getDevByAddress(inetAddress);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        if (nif == null) {
            return;
        }

        final PcapHandle handle = nif.openLive(SNAPLEN, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
        if (filter.length() != 0) {
            handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
        }

        try {
            handle.loop(PACKET_COUNT, new PacketListener() {

                @Override
                public void gotPacket(Packet packet) {
                    IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
                    TcpPacket tcpPacket = ipV4Packet.get(TcpPacket.class);


                    if (ipV4Packet.getHeader().getDstAddr().toString().equals("/192.168.0.103")) {
                        int destinationPort = tcpPacket.getHeader().getDstPort().valueAsInt();
                        if (!excludePorts.contains(String.valueOf(destinationPort))){
                            TcpPacketIncomingEvent tcpPacketIncomingEvent = new TcpPacketIncomingEvent(
                                    ipV4Packet.getHeader().getSrcAddr().toString(),
                                    tcpPacket.getHeader().getDstPort().valueAsInt());
                            runtime.getEventService().sendEventBean(tcpPacketIncomingEvent, "TcpPacketIncomingEvent");
                        };
                    }
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handle.close();
    }
}
