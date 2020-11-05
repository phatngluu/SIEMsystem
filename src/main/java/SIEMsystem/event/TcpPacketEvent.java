package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

/**
 * This class represents a packet of TCP protocol.
 * @author Luu Nguyen Phat
 */
public class TcpPacketEvent {
    /**
     * TCP header of the packet.
     */
    protected TcpPacket.TcpHeader tcpHeader;
    /**
     * IP header of the packet.
     */
    protected IpPacket.IpHeader ipHeader;

    public TcpPacketEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
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
