package SIEMsystem.collector;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.ResourceMonitorEvent;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.platform.linux.LinuxGlobalMemory;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.List;

public class ResourceCollector extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (true) {
            ResourceMonitorEvent event = monitor();
            // CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event,  "ResourceMonitorEvent");
            // System.out.println(event.getCpuLoad() + "% - " + event.getMemUsed() + "MB");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();
            // CentralProcessor processor = hal.getProcessor();
            // double loadAverage = processor.get * 100;

            GlobalMemory memory = hal.getMemory();
            long availableMemory = memory.getAvailable();
            long totalMemory = memory.getTotal();
            long usedMemory = totalMemory - availableMemory;

            System.out.println(event.getCpuLoad() + "% - " + usedMemory/1024/1024 + "MB");

        }
    }
    private ResourceMonitorEvent monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        double cpuLoad = osBean.getSystemCpuLoad() * 100;
        long memUsed = (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize() - osBean.getCommittedVirtualMemorySize())/(1024*1024); //in MB
        return new ResourceMonitorEvent(cpuLoad, memUsed);
    }
    /*
    private ResourceMonitorEvent monitor(){
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus ) {
                if (cpu.sensors != null) {
                    List<Temperature> temps = cpu.sensors.temperatures;
                    Temperature temp = temps.get(temps.size() - 1);
                    String cpuTemp = temp.name + ": " + temp.value + " C";

                    List<Load> cpuLoads = cpu.sensors.loads;
                    Load cpuLoad = cpuLoads.get(cpuLoads.size() - 2);
                    String avgCpuLoad = cpuLoad.name + ": " + cpuLoad.value + " %";

                    List<Load> memLoads = cpu.sensors.loads;
                    Load memLoad = memLoads.get(memLoads.size() - 1);
                    String avgMemLoad = memLoad.name + ": " + memLoad.value + " %";
                }
            }
        } return null;
    }*/
    /*public static void main(String[] args) {
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Load> cpuLoads = cpu.sensors.loads;
                    Load cpuLoad = cpuLoads.get(cpuLoads.size() - 2);
                    String avgCpuLoad = cpuLoad.name + ": " + cpuLoad.value + " %";
                    System.out.println(avgCpuLoad);
                    }
                }
            }
        }*/
    }
