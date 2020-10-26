package SIEMsystem.event;

public class ResourceMonitorEvent {
    private String cpuLoad;
    private String cpuTemp;
    private String memoryLoad;

    public ResourceMonitorEvent(String cpuLoad, String cpuTemp, String memoryLoad) {
        this.cpuLoad = cpuLoad;
        this.cpuTemp = cpuTemp;
        this.memoryLoad = memoryLoad;
    }
    
    public String getCpuLoad() {
        return cpuLoad;
    }

    public void setCpuLoad(String cpuLoad) {
        this.cpuLoad = cpuLoad;
    }

    public String getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(String cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public String getMemoryLoad() {
        return memoryLoad;
    }

    public void setMemoryLoad(String memoryLoad) {
        this.memoryLoad = memoryLoad;
    }

}
