package SIEMsystem.event;

import java.net.InetAddress;

public class SourceCountPortEvent {
    private InetAddress srcAddr;
    private long countPort;

    public InetAddress getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(InetAddress srcAddr) {
        this.srcAddr = srcAddr;
    }

    public long getCountPort() {
        return countPort;
    }

    public void setCountPort(long countPort) {
        this.countPort = countPort;
    }
    
}
