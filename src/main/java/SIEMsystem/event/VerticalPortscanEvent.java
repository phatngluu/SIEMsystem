package SIEMsystem.event;

import java.net.InetAddress;

/**
 * This class represents a vertical port scan event.
 * @author Luu Nguyen Phat
 */
public class VerticalPortscanEvent {
    /**
     * Destination address (the victim)
     */
    private InetAddress dstAddr;
    /**
     * Number of scanned ports on the destination address
     */
    private long countPort;

    public VerticalPortscanEvent(InetAddress dstAddr, long countPort) {
        this.dstAddr = dstAddr;
        this.countPort = countPort;
    }

    public InetAddress getDstAddr() {
        return dstAddr;
    }

    public void setDstAddr(InetAddress dstAddr) {
        this.dstAddr = dstAddr;
    }

    public long getCountPort() {
        return countPort;
    }

    public void setCountPort(long countPort) {
        this.countPort = countPort;
    }

        
}
