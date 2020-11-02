package SIEMsystem.event;

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