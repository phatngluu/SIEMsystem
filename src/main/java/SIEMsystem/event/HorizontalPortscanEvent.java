package SIEMsystem.event;

import org.pcap4j.packet.namednumber.Port;

/**
 * This class represents a horizontal port scan event.
 * @author Luu Nguyen Phat
 */
public class HorizontalPortscanEvent {
    /**
     * Destination port of the victims
     */
    private Port dstPort;
    /**
     * Number of victims are scanned
     */
    private long countDst;

    public Port getDstPort() {
        return dstPort;
    }

    public void setDstPort(Port dstPort) {
        this.dstPort = dstPort;
    }

    public long getCountDst() {
        return countDst;
    }

    public void setCountDst(long countDst) {
        this.countDst = countDst;
    }
    
}
