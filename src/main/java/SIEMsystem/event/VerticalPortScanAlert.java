package SIEMsystem.event;

import java.net.InetAddress;

public class VerticalPortScanAlert {
    InetAddress hostAddr;

    public VerticalPortScanAlert(InetAddress hostAddr) {
        this.hostAddr = hostAddr;
    }

    public InetAddress getHostAddr() {
        return hostAddr;
    }
}
