package SIEMsystem.event;

/**
 * This class represents a port scan event which is performed on a closed port.
 * @author Luu Nguyen Phat
 */
public class ClosedPortScanEvent extends TcpPacketEvent {
    public ClosedPortScanEvent(TcpPacketEvent tcpPacketEvent) {
        super(tcpPacketEvent.getIpHeader(), tcpPacketEvent.getTcpHeader());
    }
}
