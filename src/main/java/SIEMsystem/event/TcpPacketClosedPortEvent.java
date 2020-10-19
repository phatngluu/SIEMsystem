package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

public class TcpPacketClosedPortEvent {
    private TcpPacket.TcpHeader tcpHeader;
    private IpPacket.IpHeader ipHeader;

    public TcpPacketClosedPortEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
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
