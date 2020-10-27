package SIEMsystem.cep;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.HighCPUUsageAlert;
import SIEMsystem.alert.HighMemUsageAlert;
import SIEMsystem.event.HighCPUUsageEvent;
import SIEMsystem.event.HighMemoryUsageEvent;
import SIEMsystem.event.ResourceMonitorEvent;

public class ResourceModule extends Module {
  private static ResourceModule instance;

  private ResourceModule() {
  }

  public static ResourceModule getInstance() {
    if (instance == null) {
      instance = new ResourceModule();
      return instance;
    }
    return instance;
  }

  @Override
  protected void activate(CEPEngine engine) {
    engine.compileAndDeploy("select * from ResourceMonitorEvent;").addListener((newData, __, ___, ____) -> {
      engine.countEvent(ResourceMonitorEvent.class);
    });

    engine.compileAndDeploy("insert into HighCPUUsageEvent " + 
      "select a[4].cpuLoad as cpuLoad " + 
      "from pattern[every [5] (a = ResourceMonitorEvent(cpuLoad > " + engine.getProperty("RESOURCE_CPU_USAGE_THRESHOLD") + ") and not ResourceMonitorEvent(cpuLoad <= " + engine.getProperty("RESOURCE_CPU_USAGE_THRESHOLD") + "))];");
    engine.compileAndDeploy("select * from HighCPUUsageEvent;").addListener((newData, __, ___, ____) -> {
      Double CPU = (Double) newData[0].get("cpuLoad");
      HighCPUUsageAlert alert = new HighCPUUsageAlert(CPU);
      AlertManager am = AlertManager.getInstance();
      am.acceptAlert(alert);
      engine.countEvent(HighCPUUsageEvent.class);
    });

    engine.compileAndDeploy("insert into HighMemoryUsageEvent " + 
      "select a[4].memLoad as memLoad " + 
      "from pattern[every [5] (a = ResourceMonitorEvent(memLoad > " + engine.getProperty("RESOURCE_MEM_USAGE_THRESHOLD") + ") and not ResourceMonitorEvent(memLoad <= " + engine.getProperty("RESOURCE_CPU_USAGE_THRESHOLD") + "))];");
    engine.compileAndDeploy("select * from HighMemoryUsageEvent;").addListener((newData, __, ___, ____) -> {
      Double MEM = (Double) newData[0].get("memLoad");
      HighMemUsageAlert alert = new HighMemUsageAlert(MEM);
      AlertManager am = AlertManager.getInstance();
      am.acceptAlert(alert);
      engine.countEvent(HighMemoryUsageEvent.class);
      }
    );
  }
}
