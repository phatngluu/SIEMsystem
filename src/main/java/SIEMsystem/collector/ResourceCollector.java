package SIEMsystem.collector;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.ResourceMonitorEvent;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
/**
 * This class is about getting information from system resource(CPU, RAM)
 * and feed to the CEP engine.
 * @author Nguyen T. Nguyen
 */
public class ResourceCollector extends Thread {

    /**
     * this method forwards the system resource value to CEPEngine every second.
     */
    @Override
    public void run() {
        while (true) {
            ResourceMonitorEvent event = monitor();
            CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event, "ResourceMonitorEvent");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    /**
     * this method uses OperatingSystemMXBean library to get the cpuLoad
     * and oshi library to get the memLoad. The results are measured in percent(%).
     * @return new ResourceMonitorEvent object with params are cpuLoad and memLoad.
     */
    private ResourceMonitorEvent monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpuLoad = osBean.getSystemCpuLoad() * 100;
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();
        long availableMemory = memory.getAvailable();
        long totalMemory = memory.getTotal();
        double memLoad = (1 - ((double)(availableMemory)/(double)totalMemory)) * 100;
        return new ResourceMonitorEvent(cpuLoad, memLoad);
    }
}