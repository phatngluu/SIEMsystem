package SIEMsystem.event;

public class PortCountSourceEvent {
    private int dstPort;
    private long countSource;

    public int getDstPort() {
        return dstPort;
    }

    public void setDstPort(int dstPort) {
        this.dstPort = dstPort;
    }

    public long getCountSource() {
        return countSource;
    }

    public void setCountSource(long countSource) {
        this.countSource = countSource;
    }
    
}
