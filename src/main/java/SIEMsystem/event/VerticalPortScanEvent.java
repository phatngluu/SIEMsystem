package SIEMsystem.event;

import java.net.InetAddress;

public class VerticalPortScanEvent {
    InetAddress hostAddr;

    public VerticalPortScanEvent(InetAddress hostAddr) {
        this.hostAddr = hostAddr;
    }

    public InetAddress getHostAddr() {
        return hostAddr;
    }
}
