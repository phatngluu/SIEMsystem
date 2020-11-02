package SIEMsystem.alert;
/**
 * Class for representing the alert raised when the CPU usage exceeds a defined threshold value.
 * @author Nguyen Dinh Thi
 */
public class HighCPUUsageAlert extends Alert {
  private double currentCPUUsage;

  public void setCurrentCPUUsage(double cpuUsage) {
    this.currentCPUUsage = cpuUsage;
  }
  public double getCurrentCPUUsage() {
    return this.currentCPUUsage;
  }
  /**
   * Constructor of the class
   * @param cpuUsage
   *  The CPU usage percentage value at the time of the alert
   * @param timeWindow
   *  The defined period of time where the CPU usage exceeds the threshold
   */
  public HighCPUUsageAlert(double cpuUsage, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setCurrentCPUUsage(cpuUsage);
    this.setMessage("CPU usage exceeds threshold for " + timeWindow + " seconds. Current CPU usage : " + roundDown(this.getCurrentCPUUsage()) + "%");
  }
  /**
   * Class for rounding the value of a double variable to two decimal places
   * @param d
   *  The double variable which needs rounding
   * @return
   *  Returns the value of the variable rounded to two decimal places
   */
  public static double roundDown(double d) {
    return Math.floor(d * 1e2) / 1e2;
  }
}
