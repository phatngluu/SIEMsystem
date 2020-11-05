package SIEMsystem.event;

/**
 * This class represents a port scan event which is performed on a open port.
 * @author Luu Nguyen Phat
 */
public class OpenPortScanEvent extends TcpPacketEvent {
    public OpenPortScanEvent(TcpPacketEvent tcpPacketEvent) {
        super(tcpPacketEvent.getIpHeader(), tcpPacketEvent.getTcpHeader());
    }
}
