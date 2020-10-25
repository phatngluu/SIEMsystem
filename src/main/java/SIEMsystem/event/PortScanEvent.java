package SIEMsystem.event;

import java.net.InetAddress;

import org.pcap4j.packet.namednumber.Port;

public class PortScanEvent {
    private InetAddress srcAddr;
    private Port dstPort;

    public PortScanEvent(InetAddress srcAddr, Port dstPort) {
        this.srcAddr = srcAddr;
        this.dstPort = dstPort;
    }

    public InetAddress getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(InetAddress srcAddr) {
        this.srcAddr = srcAddr;
    }

    public Port getDstPort() {
        return dstPort;
    }

    public void setDstPort(Port dstPort) {
        this.dstPort = dstPort;
    }
}