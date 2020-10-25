package SIEMsystem.event;

import java.net.InetAddress;

import org.pcap4j.packet.namednumber.TcpPort;

public class PortScanEvent {
    private InetAddress srcAddr;
    private TcpPort dstPort;

    public PortScanEvent(InetAddress srcAddr, TcpPort dstPort) {
        this.srcAddr = srcAddr;
        this.dstPort = dstPort;
    }

    public InetAddress getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(InetAddress srcAddr) {
        this.srcAddr = srcAddr;
    }

    public TcpPort getDstPort() {
        return dstPort;
    }

    public void setDstPort(TcpPort dstPort) {
        this.dstPort = dstPort;
    }
}