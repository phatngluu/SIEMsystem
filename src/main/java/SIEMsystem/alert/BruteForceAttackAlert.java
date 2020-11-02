package SIEMsystem.alert;
/**
 * Class for representing a brute force attack alert
 * @author Nguyen Dinh Thi
 */
public class BruteForceAttackAlert extends Alert {
  /**
   * Constructor of the class
   * @param time
   *  Time of the alert
   * @param count
   *  Number of failed login attempts within the time window
   * @param timeWindow
   *  The period of time considered (in seconds) to raise the alert
   */
  public BruteForceAttackAlert(String time, Long count, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage(count + " failed login attempts within " + timeWindow + " seconds detected.");
  }
}