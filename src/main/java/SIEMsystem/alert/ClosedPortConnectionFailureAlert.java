package SIEMsystem.alert;
/**
 * Class for representing an alert raised when a user connects to a closed port unsuccessfully multiple times.
 * @author Nguyen Dinh Thi
 */
public class ClosedPortConnectionFailureAlert extends Alert {
  /**
   * Constructor of the class
   * @param srcAddr
   *  The source address of the connection attempt
   * @param dstAddr
   *  The destination address of the connection attempt
   * @param dstPort
   *  The destination port of the connection attempt
   * @param attempt
   *  Number of failed connection attempts within the time window
   * @param timeWindow
   *  The period of time considered (in seconds) to raise the alert
   */
  public ClosedPortConnectionFailureAlert(String srcAddr, String dstAddr, int dstPort, long attempt, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage(attempt + " failed closed port connection attempts from " + srcAddr + " to " + dstAddr + " " + dstPort + " within " + timeWindow + " seconds.");
  }
}
