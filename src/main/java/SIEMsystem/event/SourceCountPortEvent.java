package SIEMsystem.event;

import java.net.InetAddress;

public class SourceCountPortEvent {
    private InetAddress dstAddr;
    private long countPort;

    public SourceCountPortEvent(InetAddress dstAddr, long countPort) {
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
