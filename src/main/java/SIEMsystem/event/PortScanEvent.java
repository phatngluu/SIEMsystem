package SIEMsystem.event;

import java.net.InetAddress;

import org.pcap4j.packet.namednumber.Port;

public class PortScanEvent {
    private InetAddress srcAddr;
    private InetAddress dstAddr;
    private Port srcPort;
    private Port dstPort;

    public PortScanEvent(InetAddress srcAddr, InetAddress dstAddr, Port srcPort, Port dstPort) {
        this.srcAddr = srcAddr;
        this.dstAddr = dstAddr;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
    }

    public InetAddress getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(InetAddress srcAddr) {
        this.srcAddr = srcAddr;
    }

    public InetAddress getDstAddr() {
        return dstAddr;
    }

    public void setDstAddr(InetAddress dstAddr) {
        this.dstAddr = dstAddr;
    }

    public Port getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Port srcPort) {
        this.srcPort = srcPort;
    }

    public Port getDstPort() {
        return dstPort;
    }

    public void setDstPort(Port dstPort) {
        this.dstPort = dstPort;
    }
    
}