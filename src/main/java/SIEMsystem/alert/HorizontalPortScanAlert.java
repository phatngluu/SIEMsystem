package SIEMsystem.alert;

public class HorizontalPortScanAlert extends Alert {
    // private String srcAddr;
    // private long count;
    
    public HorizontalPortScanAlert(int dstPort, long count) {
        super();
        this.setMessage("Horizontal port scan detected on port " + dstPort);
    }

    // public String getSrcAddr() {
    //     return srcAddr;
    // }

    // public void setSrcAddr(String srcAddr) {
    //     this.srcAddr = srcAddr;
    // }

    // public long getCount() {
    //     return count;
    // }

    // public void setCount(long count) {
    //     this.count = count;
    // }
    
}
