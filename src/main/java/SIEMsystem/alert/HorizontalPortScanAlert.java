package SIEMsystem.alert;

public class HorizontalPortScanAlert extends Alert {
    public HorizontalPortScanAlert(int dstPort, long count) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("Horizontal port scan detected on port " + dstPort);
    }
}
