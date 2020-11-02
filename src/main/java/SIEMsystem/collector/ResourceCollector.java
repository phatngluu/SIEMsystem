package SIEMsystem.collector;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.ResourceMonitorEvent;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

public class ResourceCollector extends Thread {
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
    private ResourceMonitorEvent monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpuLoad = osBean.getSystemCpuLoad() * 100;
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();
        long availableMemory = memory.getAvailable();
        long totalMemory = memory.getTotal();
        double memLoad = (1 - ((double)(availableMemory)/(double)totalMemory)) * 100; // in MB
        return new ResourceMonitorEvent(cpuLoad, memLoad);
    }
}