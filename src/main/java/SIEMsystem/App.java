package SIEMsystem;

import java.io.FileNotFoundException;

import com.espertech.esper.common.client.configuration.Configuration;

import org.pcap4j.core.NotOpenException;
import org.pcap4j.core.PcapNativeException;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.WebserverModule;
import SIEMsystem.collector.EventCollector;

import SIEMsystem.alert.BruteForceAttackAlert;
import SIEMsystem.alert.LoginAlert;
import SIEMsystem.alert.VerticalPortScanAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.PortCountSourceEvent;
import SIEMsystem.event.SourceCountPortEvent;
import SIEMsystem.event.TcpPacketClosedPortEvent;
import SIEMsystem.event.TcpPacketEvent;
import SIEMsystem.event.TcpPacketIncomingEvent;
import SIEMsystem.event.TcpPacketIncomingEvent2;
import SIEMsystem.event.UnauthorizedEvent;
import SIEMsystem.event.VerticalPortScanEvent;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        // Setting up and run the engine
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(AccessLogEvent.class);
        configuration.getCommon().addEventType(FailedLoginEvent.class);
        configuration.getCommon().addEventType(UnauthorizedEvent.class);
        configuration.getCommon().addEventType(LoginAlert.class);
        configuration.getCommon().addEventType(BruteForceAttackAlert.class);
        configuration.getCommon().addEventType(TcpPacketEvent.class);
        configuration.getCommon().addEventType(TcpPacketIncomingEvent2.class);
        configuration.getCommon().addEventType(TcpPacketClosedPortEvent.class);
        configuration.getCommon().addEventType(VerticalPortScanEvent.class);
        configuration.getCommon().addEventType(SourceCountPortEvent.class);
        configuration.getCommon().addEventType(PortCountSourceEvent.class);
        configuration.getCommon().addEventType(VerticalPortScanAlert.class);

        CEPEngine engine = CEPEngine.getNewInstance(configuration);
        // engine.activate(WebserverModule.getInstance());
        engine.activate(PortscanModule.getInstance());
        
        // Setting up and run the collector
        EventCollector collector = new EventCollector(engine.getRuntime());
        // collector.collectAccessLog();
        // collector.collectLog();
        try {
            collector.collectPacket();
        } catch (PcapNativeException | NotOpenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
