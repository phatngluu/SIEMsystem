package SIEMsystem.cep;

import java.net.InetAddress;
import org.pcap4j.packet.namednumber.Port;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.BlockPortScanAlert;
import SIEMsystem.alert.ClosedPortConnectionFailureAlert;
import SIEMsystem.alert.HorizontalPortScanAlert;
import SIEMsystem.alert.VerticalPortScanAlert;
import SIEMsystem.event.BlockPortScanEvent;
import SIEMsystem.event.ClosedPortScanEvent;
import SIEMsystem.event.OpenPortScanEvent;
import SIEMsystem.event.HorizontalPortscanEvent;
import SIEMsystem.event.PortScanEvent;
import SIEMsystem.event.VerticalPortscanEvent;
import SIEMsystem.event.TcpPacketEvent;
import SIEMsystem.event.ClosedPortConnectionFailureEvent;

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
        engine.compileAndDeploy("select * from TcpPacketEvent;").addListener((newData, __, ___, ____) -> {
            engine.countEvent(TcpPacketEvent.class);
        });

        /* Closed Port Scan Event */
        engine.compileAndDeploy("insert into ClosedPortScanEvent\n" + "select a.ipHeader, a.tcpHeader from pattern [\n"
                + "    every a=TcpPacketEvent(tcpHeader.syn = true and tcpHeader.ack = false) ->\n"
                + "    b=TcpPacketEvent(\n" + "        tcpHeader.rst = true and\n"
                + "        ipHeader.srcAddr = a.ipHeader.dstAddr and\n"
                + "        ipHeader.dstAddr = a.ipHeader.srcAddr and\n"
                + "        tcpHeader.srcPort = a.tcpHeader.dstPort and\n"
                + "        tcpHeader.dstPort = a.tcpHeader.srcPort\n" + "    )\n"
                + "    where timer:within(100 millisecond)\n" + "];\n");

        /* Open Port Scan Event */
        engine.compileAndDeploy("insert into OpenPortScanEvent\n" + "select a.ipHeader, a.tcpHeader from pattern [\n"
                + "    every a=TcpPacketEvent(tcpHeader.syn = false and tcpHeader.ack = true) ->\n"
                + "    b=TcpPacketEvent(\n" + "        tcpHeader.rst = true and\n"
                + "        tcpHeader.ack = true and\n" + "        ipHeader.srcAddr = a.ipHeader.srcAddr and\n"
                + "        ipHeader.dstAddr = a.ipHeader.dstAddr and\n"
                + "        tcpHeader.srcPort = a.tcpHeader.srcPort and\n"
                + "        tcpHeader.dstPort = a.tcpHeader.dstPort\n" + "    )\n"
                + "    where timer:within(100 millisecond)\n" + "];\n");

        /* Port Scan Event */
        engine.compileAndDeploy("insert into PortScanEvent\n"
                + "select ipHeader.dstAddr as dstAddr, tcpHeader.dstPort as dstPort from ClosedPortScanEvent;")
                .addListener((newData, __, ___, ____) -> {
                    engine.countEvent(ClosedPortScanEvent.class);
                });
        engine.compileAndDeploy("insert into PortScanEvent\n"
                + "select ipHeader.dstAddr as dstAddr, tcpHeader.dstPort as dstPort from OpenPortScanEvent;").addListener((newData, __, ___, ____) -> {
                    engine.countEvent(OpenPortScanEvent.class);
                });
        engine.compileAndDeploy("select * from PortScanEvent;").addListener((newData, __, ___, ____) -> {
                engine.countEvent(PortScanEvent.class);
        });

        /* Closed Port Connection Failure*/
        engine.compileAndDeploy("insert into ClosedPortConnectionFailureEvent\n" +
                "select ipHeader.srcAddr as srcAddr, tcpHeader.srcPort as srcPort, ipHeader.dstAddr as dstAddr, tcpHeader.dstPort as dstPort\n" + 
                "from ClosedPortScanEvent#time(" + engine.getProperty("PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_TIME_WINDOW_IN_SECONDS") + ");");
        engine.compileAndDeploy("select count(*) as countAttempt, srcAddr, srcPort, dstAddr, dstPort\n" + 
                "from ClosedPortConnectionFailureEvent#time(" + engine.getProperty("PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_TIME_WINDOW_IN_SECONDS") + ")\n" +
                "group by srcAddr, dstAddr, dstPort\n" +
                "having count(*) > " + engine.getProperty("PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_ATTEMPT_THRESHOLD") + ";").addListener((newData, __, ___, ____) -> {
                        String SRCADDR = ((InetAddress) newData[0].get("srcAddr")).toString();
                        String DSTADDR = ((InetAddress) newData[0].get("dstAddr")).toString();
                        int DSTPORT = ((Port) newData[0].get("dstPort")).valueAsInt();
                        long ATTEMPT = (long) newData[0].get("countAttempt");
                        int TIMEWINDOW = Integer.valueOf(engine.getProperty("PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_TIME_WINDOW_IN_SECONDS"));
                        AlertManager alertManager = AlertManager.getInstance();
                        alertManager.acceptAlert(new ClosedPortConnectionFailureAlert(SRCADDR, DSTADDR, DSTPORT, ATTEMPT, TIMEWINDOW));
                        engine.countEvent(ClosedPortConnectionFailureEvent.class);
                });

        /* Vertical Port Scan */
        engine.compileAndDeploy(
                "insert into VerticalPortscanEvent\n" + "select dstAddr, count(distinct(dstPort)) as countPort\n"
                        + "from PortScanEvent#time(" + engine.getProperty("PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS")
                        + ")\n" + "group by dstAddr\n" + "having count(distinct(dstPort)) >= "
                        + engine.getProperty("PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS") + ";");

        engine.compileAndDeploy("select * from VerticalPortscanEvent output last every "
                + engine.getProperty("PORTSCAN_V_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    String srcAddr = ((InetAddress) newData[0].get("dstAddr")).toString();
                    long countPort = (long) newData[0].get("countPort");
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new VerticalPortScanAlert(srcAddr, countPort));
                    engine.countEvent(VerticalPortscanEvent.class);
                });

        /* Horizontal Port Scan */
        engine.compileAndDeploy(
                "insert into HorizontalPortscanEvent\n" + "select dstPort, count(distinct(dstAddr)) as countSource\n"
                        + "from PortScanEvent#time(" + engine.getProperty("PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS")
                        + ")\n" + "group by dstPort\n" + "having count(distinct(dstAddr)) >= "
                        + engine.getProperty("PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS") + ";");

        engine.compileAndDeploy("select * from HorizontalPortscanEvent output last every "
                + engine.getProperty("PORTSCAN_H_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    int dstPort = ((Port) newData[0].get("dstPort")).valueAsInt();
                    long countSource = (long) newData[0].get("countSource");
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new HorizontalPortScanAlert(dstPort, countSource));
                    engine.countEvent(HorizontalPortscanEvent.class);
                });

        /* Block port scan */
        engine.compileAndDeploy("insert into BlockPortScanEvent\n" + "select \"Vertical\" as portScan\n"
                + "from VerticalPortscanEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS")
                + ")\n");
        engine.compileAndDeploy("insert into BlockPortScanEvent\n" + "select \"Horizontal\" as portScan\n"
                + "from HorizontalPortscanEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS")
                + ")\n");
        engine.compileAndDeploy("select portScan, count(*) as countBlock from BlockPortScanEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS") + ")\n"
                + "where exists(select * from BlockPortScanEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS") + ") where portScan = \"Vertical\")\n"
                + "and exists(select * from BlockPortScanEvent#time(" + engine.getProperty("PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS") + ") where portScan = \"Horizontal\")\n"
                + "output last every " + engine.getProperty("PORTSCAN_H_THROW_ALERT_EACH_SECONDS") + " seconds")
                .addListener((newData, __, ___, ____) -> {
                    System.out.println(newData[0].get("countBlock") + " item(s) in BlockPortScanEvent.");
                    AlertManager alertManager = AlertManager.getInstance();
                    alertManager.acceptAlert(new BlockPortScanAlert());
                    engine.countEvent(BlockPortScanEvent.class);
                });
    }
}
