package SIEMsystem.alert;
/**
 * Class for representing the alert raised when the memory usage exceeds a defined threshold value.
 * @author Nguyen Dinh Thi
 */
public class HighMemUsageAlert extends Alert {
  private double currentMemUsage;

  public void setCurrentMemUsage(double memUsage) {
    this.currentMemUsage = memUsage;
  }
  public double getCurrentMemUsage() {
    return this.currentMemUsage;
  }
  /**
   * Constructor of the class
   * @param memUsage
   *  The memory usage percentage value at the time of the alert
   * @param timeWindow
   *  The defined period of time where the CPU usage exceeds the threshold
   */
  public HighMemUsageAlert(double memUsage, int timeWindow) {
    super();
    this.name = this.getClass().getSimpleName();
    this.setCurrentMemUsage(memUsage);
    this.setMessage("Memory usage exceeds threshold for " + timeWindow + " seconds. Current memory usage : " + roundDown(this.getCurrentMemUsage()) + "%");
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