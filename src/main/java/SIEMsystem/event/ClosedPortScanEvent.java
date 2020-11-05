package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

/**
 * This class represents a port scan event which is performed on a closed port.
 * @author Luu Nguyen Phat
 */
public class ClosedPortScanEvent extends TcpPacketEvent {
    public ClosedPortScanEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
        super(ipHeader, tcpHeader);
    }
}
