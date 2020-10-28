package SIEMsystem.alert;

public class VerticalPortScanAlert extends Alert {
    public VerticalPortScanAlert(String dstAddr, long count) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("Vertical port scan detected on " + dstAddr);
    }
}
