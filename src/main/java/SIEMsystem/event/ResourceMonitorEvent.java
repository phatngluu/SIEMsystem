package SIEMsystem.event;

public class ResourceMonitorEvent {
    //private List<Cpu> cpu;
    private String cpuTemp;
    private String cpuFan;
    private String memoryLoad;
    private String diskLoad;

    public ResourceMonitorEvent(String cpuTemp, String cpuFan, String memoryLoad, String diskLoad) {
        //this.cpu = cpu;
        this.cpuTemp = cpuTemp;
        this.cpuFan = cpuFan;
        this.memoryLoad = memoryLoad;
        this.diskLoad = diskLoad;
    }

    public String getCpuTemp() {
        return cpuTemp;
    }

    public String getCpuFan() {
        return cpuFan;
    }

    public String getMemoryLoad() {
        return memoryLoad;
    }

    public String getDiskLoad() {
        return diskLoad;
    }
}
