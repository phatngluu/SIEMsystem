package SIEMsystem.event;

import java.net.InetAddress;

public class VerticalPortscanEvent {
    private InetAddress dstAddr;
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
