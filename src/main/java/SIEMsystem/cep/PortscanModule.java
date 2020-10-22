package SIEMsystem.cep;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.BlockPortScanAlert;
import SIEMsystem.alert.HorizontalPortScanAlert;
import SIEMsystem.alert.VerticalPortScanAlert;
import SIEMsystem.event.BlockPortScanEvent;
import SIEMsystem.event.PortCountSourceEvent;
import SIEMsystem.event.SourceCountPortEvent;
import SIEMsystem.event.TcpPacketIncomingEvent;

public class PortscanModule extends Module {
    private static PortscanModule instance;

    private PortscanModule() {
    }

    public static PortscanModule getInstance() {
        if (instance == null) {
            instance = new PortscanModule();
            return instance;
        }
        return instance;
    }

    @Override
    protected void activate(CEPEngine engine) {

        engine.compileAndDeploy("select * from TcpPacketIncomingEvent;").addListener((newData, __, ___, ____) -> {
            engine.countEvent(TcpPacketIncomingEvent.class);
        });
        /* Vertical Port Scan */

        engine.compileAndDeploy("insert into SourceCountPortEvent\n"
                + "select srcAddr, count(distinct(dstPort)) as countPort\n" + "from TcpPacketIncomingEvent#time("
                + engine.getProperty("PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS") + ")\n" + "group by srcAddr\n"
                + "having count(distinct(dstPort)) >= " + engine.getProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS")
                + ";");

        engine.compileAndDeploy("select * from SourceCountPortEvent output last every "
                + engine.getProperty("PORTSCAN_V_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    String srcAddr = (String) newData[0].get("srcAddr");
                    long countPort = (long) newData[0].get("countPort");
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new VerticalPortScanAlert(srcAddr, countPort));
                    engine.countEvent(SourceCountPortEvent.class);
                });

        /* Horizontal Port Scan */

        engine.compileAndDeploy("insert into PortCountSourceEvent\n"
                + "select dstPort, count(distinct(srcAddr)) as countSource\n" + "from TcpPacketIncomingEvent#time("
                + engine.getProperty("PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS") + ")\n" + "group by dstPort\n"
                + "having count(distinct(srcAddr)) >= " + engine.getProperty("PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS")
                + ";");

        engine.compileAndDeploy("select * from PortCountSourceEvent output last every "
                + engine.getProperty("PORTSCAN_H_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    int dstPort = (int) newData[0].get("dstPort");
                    long countSource = (long) newData[0].get("countSource");
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new HorizontalPortScanAlert(dstPort, countSource));
                    engine.countEvent(PortCountSourceEvent.class);
                });

        /* Block port scan */

        engine.compileAndDeploy("insert into BlockPortScanEvent\n" + "select \"Vertical\" as portScan\n"
                + "from SourceCountPortEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS")
                + ")\n");
        engine.compileAndDeploy("insert into BlockPortScanEvent\n" + "select \"Horizontal\" as portScan\n"
                + "from PortCountSourceEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS")
                + ")\n");
        engine.compileAndDeploy("select * from BlockPortScanEvent\n"
                + "where exists(select * from BlockPortScanEvent where portScan = \"Vertical\")\n"
                + "and exists(select * from BlockPortScanEvent where portScan = \"Horizontal\")\n"
                + "output last every " + engine.getProperty("PORTSCAN_H_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new BlockPortScanAlert());
                    engine.countEvent(BlockPortScanEvent.class);
                });
    }
}
