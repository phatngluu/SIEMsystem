package SIEMsystem.event;

public class SourceCountPortEvent {
    private String srcAddr;
    private long countPort;

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public long getCountPort() {
        return countPort;
    }

    public void setCountPort(long countPort) {
        this.countPort = countPort;
    }
    
}
