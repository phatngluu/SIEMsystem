package SIEMsystem.event;

public class ResourceMonitorEvent {
    private double cpuLoad;
    private double memLoad;
    private long memUsed;

    public ResourceMonitorEvent(double cpuLoad, long memUsed) {
        this.cpuLoad = cpuLoad;
        this.memUsed = memUsed;
    }

    public double getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public double getMemLoad() {
        return memLoad;
    }

    public void setMemLoad(double memLoad) {
        this.memLoad = memLoad;
    }

    public long getMemUsed() {
        return memUsed;
    }

    public void setMemUsed(long memUsed) {
        this.memUsed = memUsed;
    }
}
