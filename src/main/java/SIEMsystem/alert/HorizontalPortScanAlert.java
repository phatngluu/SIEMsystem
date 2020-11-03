package SIEMsystem.alert;
/**
 * Class for representing the alert raised when a horizontal port scan is detected.
 * @author Nguyen Dinh Thi
 */
public class HorizontalPortScanAlert extends Alert {
    /**
     * Constructor of the class
     * @param dstPort
     *  The port number scanned
     * @param count
     *  Number of hosts whose ports are scanned
     */
    public HorizontalPortScanAlert(int dstPort, long count) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("Horizontal port scan detected on port " + dstPort);
    }
}
