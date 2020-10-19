package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

public class TcpPacketIncomingEvent {
    private IpPacket.IpHeader ipHeader;
    private TcpPacket.TcpHeader tcpHeader;

    public TcpPacketIncomingEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
        this.ipHeader = ipHeader;
        this.tcpHeader = tcpHeader;
    }

    public IpPacket.IpHeader getIpHeader() {
        return ipHeader;
    }

    public TcpPacket.TcpHeader getTcpHeader() {
        return tcpHeader;
    }
}