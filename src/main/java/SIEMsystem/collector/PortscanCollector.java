package SIEMsystem.collector;

import org.pcap4j.core.*;
import org.pcap4j.packet.IpV4Packet;
import org.pcap4j.packet.Packet;
import org.pcap4j.packet.TcpPacket;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.TcpPacketEvent;

public class PortscanCollector extends Thread {
    @Override
    public void run() {
        String filter = "tcp"; // Filter TCP packets
        int PACKET_COUNT = -1; // Infinite
        int READ_TIMEOUT = 100; // Milisec
        int SNAPLEN = 65536; // Bytes
        String nifName = CEPEngine.getCreatedInstance().getProperty("PORTSCAN_NETWORK_INTERFACE_NAME");

        try {
            PcapNetworkInterface nif = Pcaps.getDevByName(nifName);
            if (nif == null) System.out.println("Interface " + nifName + " is unavailable.");

            PcapHandle handle = nif.openLive(SNAPLEN, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, READ_TIMEOUT);
            handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
            handle.loop(PACKET_COUNT, new PacketListener() {
                @Override
                public void gotPacket(Packet packet) {
                    IpV4Packet ipV4Packet = packet.get(IpV4Packet.class);
                    TcpPacket tcpPacket = ipV4Packet.get(TcpPacket.class);

                    TcpPacketEvent event = new TcpPacketEvent(
                            ipV4Packet.getHeader(),
                            tcpPacket.getHeader()
                    );
                    CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event, "TcpPacketEvent");
                }
            });
            handle.close();
        } catch (PcapNativeException | NotOpenException | InterruptedException e) {
            if (e instanceof NotOpenException){
                System.out.println("Set filter failed.");
            }
            if (e instanceof InterruptedException){
                System.out.println("Set loop failed.");
            }
            e.printStackTrace();
        }
    }
}
