package SIEMsystem.event;

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
