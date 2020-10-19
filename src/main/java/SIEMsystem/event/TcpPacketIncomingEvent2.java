package SIEMsystem.event;

public class TcpPacketIncomingEvent2 {
    private String srcAddr;
    private int dstPort;

    public TcpPacketIncomingEvent2(String srcAddr, int dstPort) {
        this.srcAddr = srcAddr;
        this.dstPort = dstPort;
    }

    public String getSrcAddr() {
        return srcAddr;
    }

    public void setSrcAddr(String srcAddr) {
        this.srcAddr = srcAddr;
    }

    public int getDstPort() {
        return dstPort;
    }

    public void setDstPort(int dstPort) {
        this.dstPort = dstPort;
    }
}