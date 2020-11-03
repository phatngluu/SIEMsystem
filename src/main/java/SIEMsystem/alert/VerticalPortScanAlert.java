package SIEMsystem.alert;
/**
 * Class for representing the alert raised when a vertical port scan is detected.
 * @author Nguyen Dinh Thi
 */
public class VerticalPortScanAlert extends Alert {
    /**
     * Constructor of the class
     * @param dstAddr
     *  The IP address of the host which are scanned
     * @param count
     *  Number of ports of the host which are scanned
     */
    public VerticalPortScanAlert(String dstAddr, long count) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("Vertical port scan detected on " + dstAddr);
    }
}
