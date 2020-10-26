package SIEMsystem.collector;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.ResourceMonitorEvent;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.List;

public class ResourceCollector extends Thread {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - start > 5000){
                ResourceMonitorEvent event = monitor();
                CEPEngine.getCreatedInstance().getRuntime().getEventService().sendEventBean(event, "ResourceMonitorEvent");
                start = System.currentTimeMillis();
            }
        }
    }
    private ResourceMonitorEvent monitor() {
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(
                OperatingSystemMXBean.class);
                double totalCpuLoad = osBean.getSystemCpuLoad() * 100;
                long mem = (osBean.getTotalPhysicalMemorySize() - osBean.getFreePhysicalMemorySize())/(1024*1024); //in MB
                return null;
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
