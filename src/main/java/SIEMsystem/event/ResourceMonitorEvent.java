package SIEMsystem.event;

public class ResourceMonitorEvent {
    private double totalCpuLoad;
    private long mem;

    public ResourceMonitorEvent(double totalCpuLoad, long mem) {
        this.totalCpuLoad = totalCpuLoad;
        this.mem = mem;
    }
    
    public double getTotalCpuLoad() {
        return totalCpuLoad;
    }

    public void setTotalCpuLoad(double totalCpuLoad) {
        this.totalCpuLoad = totalCpuLoad;
    }

    public long getMem() {
        return mem;
    }

    public void setMem(long mem) {
        this.mem = mem;
    }

}
