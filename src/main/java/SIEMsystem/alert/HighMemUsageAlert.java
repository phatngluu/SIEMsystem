package SIEMsystem.alert;

public class HighMemUsageAlert extends Alert {
  private double currentMemUsage;

  public void setCurrentMemUsage(double memUsage) {
    this.currentMemUsage = memUsage;
  }
  public double getCurrentMemUsage() {
    return this.currentMemUsage;
  }

  public HighMemUsageAlert(double memUsage, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setCurrentMemUsage(memUsage);
    this.setMessage("Memory usage exceeds threshold for " + timeWindow + " seconds. Current memory usage : " + roundDown(this.getCurrentMemUsage()) + "%");
  }

  public static double roundDown(double d) {
    return Math.floor(d * 1e2) / 1e2;
  }
}