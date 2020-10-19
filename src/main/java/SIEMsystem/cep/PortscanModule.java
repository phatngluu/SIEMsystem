package SIEMsystem.cep;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

import org.pcap4j.packet.IpPacket;
import org.pcap4j.packet.TcpPacket;
import org.pcap4j.packet.IpPacket.IpHeader;
import org.pcap4j.packet.TcpPacket.TcpHeader;

import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;

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

        // engine.compileAndDeploy(
        // "select ipHeader.srcAddr as srcAddr, count(distinct tcpHeader.dstPort) as
        // countPort from TcpPacketIncomingEvent#length(1000) " +
        // "group by ipHeader.srcAddr"
        // ).addListener((newData, __, ___, ____) -> {
        // Inet4Address srcAddr = (Inet4Address) newData[0].get("srcAddr");
        // int countPort = (int) newData[0].get("countPort");

        // System.out.println("Event: " + srcAddr + " -> " + countPort);
        // });

        engine.compileAndDeploy("select * from TcpPacketIncomingEvent2");

        engine.compileAndDeploy(
            "create table IncomingTable(srcAddr string primary key, countPort count(*));\n" + 
            "into table IncomingTable\n" + 
            "select srcAddr, count(*) as countPort\n" + 
            "from TcpPacketIncomingEvent2#length(1000)\n" + 
            "group by srcAddr;\n" +
            "select * from IncomingTable"
        ).addListener((newData, __, ___, ____) -> {
            Inet4Address srcAddr = (Inet4Address) newData[0].get("srcAddr");
            int countPort = (int) newData[0].get("countPort");

            System.out.println("Event: " + srcAddr + " -> " + countPort);
        });

        // engine.compileAndDeploy("select * from TcpPacketClosedPortEvent;")
        // .addListener((newData, oldData, stmt, rt) -> {
        // IpHeader ipHeader = (IpPacket.IpHeader) newData[0].get("ipHeader");
        // System.out.println(ipHeader.getSrcAddr() + " -> " + ipHeader.getDstAddr());
        // });
        // engine.compileAndDeploy(
        // "insert into TcpPacketClosedPortEvent\n"+
        // "select a.ipHeader, a.tcpHeader from pattern [\n" +
        // " every a=TcpPacketEvent(tcpHeader.syn = true and tcpHeader.ack = false)
        // ->\n"+
        // " b=TcpPacketEvent(\n" +
        // " tcpHeader.rst = true and\n" +
        // " ipHeader.srcAddr = a.ipHeader.dstAddr and\n" +
        // " ipHeader.dstAddr = a.ipHeader.srcAddr and\n" +
        // " tcpHeader.srcPort = a.tcpHeader.dstPort and\n" +
        // " tcpHeader.dstPort = a.tcpHeader.srcPort\n" +
        // " )\n" +
        // " where timer:within(100 millisecond)\n" +
        // "];\n"
        // );

        // engine.compileAndDeploy(
        // "insert into TcpPacketClosedPortEvent\n"+
        // "select ipHeader, tcpHeader from TcpPacketEvent(tcpHeader.rst = true)"
        // );
    }
}
