package SIEMsystem.alert;

public class HighCPUUsageAlert extends Alert {
  private double currentCPUUsage;

  public void setCurrentCPUUsage(double cpuUsage) {
    this.currentCPUUsage = cpuUsage;
  }
  public double getCurrentCPUUsage() {
    return this.currentCPUUsage;
  }

  public HighCPUUsageAlert(double cpuUsage) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setCurrentCPUUsage(cpuUsage);
    this.setMessage("CPU usage exceeds threshold for 5 seconds. Current CPU usage : " + roundDown(this.getCurrentCPUUsage()) + "%");
  }

  public static double roundDown(double d) {
    return Math.floor(d * 1e2) / 1e2;
  }
}
