package SIEMsystem.event;

import org.pcap4j.packet.namednumber.Port;

public class PortCountSourceEvent {
    private Port dstPort;
    private long countSource;

    public Port getDstPort() {
        return dstPort;
    }

    public void setDstPort(Port dstPort) {
        this.dstPort = dstPort;
    }

    public long getCountSource() {
        return countSource;
    }

    public void setCountSource(long countSource) {
        this.countSource = countSource;
    }
    
}
