package SIEMsystem.alert;

public class VerticalPortScanAlert extends Alert {
    // private String srcAddr;
    // private long count;
    
    public VerticalPortScanAlert(String srcAddr, long count) {
        super();
        this.setMessage("Vertical port scan detected from " + srcAddr);
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
