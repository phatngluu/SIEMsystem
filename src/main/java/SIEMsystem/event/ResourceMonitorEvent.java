package SIEMsystem.event;

/**
 * This class represents an event of system resources (ie. CPU, RAM load).
 * @author Luu Nguyen Phat
 * @author Nguyen Tri Nguyen
 */
public class ResourceMonitorEvent {
    private double cpuLoad;
    private double memLoad;

    public ResourceMonitorEvent(double cpuLoad, double memLoad) {
        this.cpuLoad = cpuLoad;
        this.memLoad = memLoad;
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
}
