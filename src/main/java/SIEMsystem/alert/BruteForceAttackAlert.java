package SIEMsystem.alert;

public class BruteForceAttackAlert extends Alert {
  public BruteForceAttackAlert(String time, Long count) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage(count + " failed login attempts within 5 minutes detected.");
  }
}