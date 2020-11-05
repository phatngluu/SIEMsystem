package SIEMsystem.event;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;

/**
 * This class represents a port scan event which is performed on a open port.
 * @author Luu Nguyen Phat
 */
public class OpenPortScanEvent extends TcpPacketEvent {
    public OpenPortScanEvent(IpPacket.IpHeader ipHeader, TcpPacket.TcpHeader tcpHeader) {
        super(ipHeader, tcpHeader);
    }
}
