package SIEMsystem.alert;

public class ClosedPortConnectionFailureAlert extends Alert {
  public ClosedPortConnectionFailureAlert(String srcAddr, String dstAddr, int dstPort, long attempt, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage(attempt + " failed closed port connection attempts from " + srcAddr + " to " + dstAddr + " " + dstPort + " within " + timeWindow + " seconds.");
  }
}
