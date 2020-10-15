package SIEMsystem.alert;

public class BruteForceAttackAlert2 extends Alert {
  public BruteForceAttackAlert2(String time, Long count) {
    super();
    StringBuilder sb = new StringBuilder();

    sb.append("ALERT : " + count + " failed login attempts within 5 minutes detected\n");
    sb.append("POTENTIAL ATTACK\n");
    sb.append("Time : " + time + "\n");
    this.setMessage(sb.toString());
  }
}
