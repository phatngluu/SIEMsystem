package SIEMsystem.alert;

public class BruteForceAttackAlert extends Alert {
  public BruteForceAttackAlert(String time, Long count, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage(count + " failed login attempts within " + timeWindow + " seconds detected.");
  }
}