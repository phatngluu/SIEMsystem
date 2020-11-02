package SIEMsystem.event;
/**
 * Class for representing the event in which CPU usage exceeds a threshold for a defined time window
 * @author Nguyen Dinh Thi
 */
public class HighCPUUsageEvent {
  private double cpuLoad;

  public double getCpuLoad() {
    return this.cpuLoad;
  }
  public void setCpuLoad(double cpuLoad) {
    this.cpuLoad = cpuLoad;
  }

  public HighCPUUsageEvent(double cpuLoad) {
    this.setCpuLoad(cpuLoad);
  }
}
