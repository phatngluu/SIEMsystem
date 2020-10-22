package SIEMsystem.collector;

import java.net.InetAddress;

import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.TcpPacketIncomingEvent;

public class WebserverCollector extends Thread {
    @Override
    public void run() {
        String filter = "tcp"; // Filter TCP packets
        int PACKET_COUNT = -1; // Infinite
        int READ_TIMEOUT = 100; // Milisec
        int SNAPLEN = 65536; // Bytes
        String excludePorts = CEPEngine.getCreatedInstance().getProperty("PORTSCAN_EXCLUDE_PORTS");

        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.0.103");
            PcapNetworkInterface nif = Pcaps.getDevByAddress(inetAddress);

            PcapHandle handle = nif.openLive(SNAPLEN, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
            handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
            handle.loop(PACKET_COUNT, new PacketListener() {
                @Override
                public void gotPacket(Packet packet) {
                    IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
                    TcpPacket tcpPacket = ipV4Packet.get(TcpPacket.class);

                    if (ipV4Packet.getHeader().getDstAddr().toString().equals("/192.168.0.103")) {
                        int destinationPort = tcpPacket.getHeader().getDstPort().valueAsInt();
                        if (!excludePorts.contains(String.valueOf(destinationPort))) {
                            TcpPacketIncomingEvent tcpPacketIncomingEvent = new TcpPacketIncomingEvent(
                                    ipV4Packet.getHeader().getSrcAddr().toString(),
                                    tcpPacket.getHeader().getDstPort().valueAsInt());
                            CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(tcpPacketIncomingEvent,
                                    "TcpPacketIncomingEvent");
                        }
                        ;
                    }
                }
            });
            handle.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
