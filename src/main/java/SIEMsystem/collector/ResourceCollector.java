package SIEMsystem.collector;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.ResourceMonitorEvent;
import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Components;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.components.Disk;
import com.profesorfalken.jsensors.model.sensors.Fan;
import com.profesorfalken.jsensors.model.sensors.Load;
import com.profesorfalken.jsensors.model.sensors.Temperature;

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
    private String monitor(){
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        List<Disk> disks = components.disks;
        if (cpus != null && disks != null) {
            for (final Cpu cpu : cpus ) {
                if (cpu.sensors != null) {
                    List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        String cpuTemp = temp.name + ": " + temp.value + " C";
                    }

                    List<Fan> fans = cpu.sensors.fans;
                    for (final Fan fan : fans) {
                        String cpuFan = fan.name + ": " + fan.value + " RPM";
                    }
                    List<Load> loads = cpu.sensors.loads;
                    for (final Load load : loads) {
                        String memoryLoad = load.name + ": " + load.value + " %";
                    }
                }
            }
            for (final Disk disk : disks) {
                if (disk.sensors != null) {
                    //String diskName = disk.name;
                    List<Load> loads = disk.sensors.loads;
                    for (final Load load : loads) {
                    String diskLoad = load.name + ": " + load.value + " %";
                    }
                }
            }
        } return null;
    }
    public static void main(String[] args) {
        Components components = JSensors.get.components();

        List<Cpu> cpus = components.cpus;
        if (cpus != null) {
            for (final Cpu cpu : cpus) {
                System.out.println("Found CPU component: " + cpu.name);
                if (cpu.sensors != null) {
                    System.out.println("Sensors: ");

                    //Print temperatures
                    List<Temperature> temps = cpu.sensors.temperatures;
                    for (final Temperature temp : temps) {
                        System.out.println(temp.name + ": " + temp.value + " C");
                    }

                    //Print fan speed
                    List<Fan> fans = cpu.sensors.fans;
                    for (final Fan fan : fans) {
                        System.out.println(fan.name + ": " + fan.value + " RPM");
                    }

                    //Print load
                    List<Load> load = cpu.sensors.loads;
                    for (final Load loads : load) {
                        System.out.println(loads.name + ": " + loads.value + " %");
                    }
                }
            }
        }
    }
}
