package SIEMsystem.event;

public class ResourceMonitorEvent {
    private String avgCpuLoad;
    private String cpuTemp;
    private String avgMemLoad;

    public ResourceMonitorEvent(String avgCpuLoad, String cpuTemp, String avgMemLoad) {
        this.avgCpuLoad = avgCpuLoad;
        this.cpuTemp = cpuTemp;
        this.avgMemLoad = avgMemLoad;
    }
    
    public String getAvgCpuLoad() {
        return avgCpuLoad;
    }

    public void setAvgCpuLoad(String avgCpuLoad) {
        this.avgCpuLoad = avgCpuLoad;
    }

    public String getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(String cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public String getAvgMemLoad() {
        return avgMemLoad;
    }

    public void setAvgMemLoad(String avgMemLoad) {
        this.avgMemLoad = avgMemLoad;
    }

}
