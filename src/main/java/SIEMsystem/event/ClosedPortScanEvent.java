package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

/**
 * This class represents a port scan event which is performed on a closed port.
 * @author Luu Nguyen Phat
 */
public class ClosedPortScanEvent {
    /**
     * TCP header of the packet.
     */
    private TcpPacket.TcpHeader tcpHeader;
    /**
     * IP header of the packet.
     */
    private IpPacket.IpHeader ipHeader;

    public ClosedPortScanEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
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
