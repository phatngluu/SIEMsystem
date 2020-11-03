package SIEMsystem.event;
/**
 * Class for representing the event in which memory usage exceeds a threshold for a defined time window
 * @author Nguyen Dinh Thi
 */
public class HighMemoryUsageEvent {
  private double memLoad;

  public double getMemLoad() {
    return this.memLoad;
  }
  public void setMemLoad(double memLoad) {
    this.memLoad = memLoad;
  }

  public HighMemoryUsageEvent(double cpuLoad) {
    this.setMemLoad(cpuLoad);
  }
}