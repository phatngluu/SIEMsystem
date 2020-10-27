package SIEMsystem;

import java.io.FileNotFoundException;

import SIEMsystem.event.*;
import com.espertech.esper.common.client.configuration.Configuration;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.ResourceModule;
import SIEMsystem.cep.WebserverModule;
import SIEMsystem.collector.WebserverCollector;
import SIEMsystem.collector.PortscanCollector;
import SIEMsystem.collector.ResourceCollector;
import SIEMsystem.event.ConsecutiveFailedLoginEvent;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.BlockPortScanEvent;
import SIEMsystem.event.BruteForceAttackEvent;
import SIEMsystem.event.ClosedPortScanEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.OpenPortScanEvent;
import SIEMsystem.event.PortCountSourceEvent;
import SIEMsystem.event.SourceCountPortEvent;
import SIEMsystem.event.TcpPacketEvent;
import SIEMsystem.event.PortScanEvent;
import SIEMsystem.event.ForbiddenEvent;
import SIEMsystem.event.HighCPUUsageEvent;
import SIEMsystem.event.HighMemoryUsageEvent;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        // Setting up and run the engine
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(AccessLogEvent.class);
        configuration.getCommon().addEventType(FailedLoginEvent.class);
        configuration.getCommon().addEventType(ForbiddenEvent.class);
        configuration.getCommon().addEventType(ConsecutiveFailedLoginEvent.class);
        configuration.getCommon().addEventType(BruteForceAttackEvent.class);
        configuration.getCommon().addEventType(SourceCountPortEvent.class);
        configuration.getCommon().addEventType(PortCountSourceEvent.class);
        configuration.getCommon().addEventType(BlockPortScanEvent.class);
        configuration.getCommon().addEventType(TcpPacketEvent.class);
        configuration.getCommon().addEventType(OpenPortScanEvent.class);
        configuration.getCommon().addEventType(ClosedPortScanEvent.class);
        configuration.getCommon().addEventType(PortScanEvent.class);
        configuration.getCommon().addEventType(ResourceMonitorEvent.class);
        configuration.getCommon().addEventType(HighCPUUsageEvent.class);
        configuration.getCommon().addEventType(HighMemoryUsageEvent.class);

        CEPEngine engine = CEPEngine.getNewInstance(configuration);
        // engine.activate(WebserverModule.getInstance());
        // engine.activate(PortscanModule.getInstance());
        engine.activate(ResourceModule.getInstance());

        // WebserverCollector webserverCollector = new WebserverCollector();
        // PortscanCollector portscanCollector = new PortscanCollector();
        ResourceCollector resourceCollector = new ResourceCollector();
        
        // webserverCollector.start();
        // portscanCollector.start();
        resourceCollector.start();

        // long prev = System.currentTimeMillis();
        // while (true){
        //     if (System.currentTimeMillis() - prev > 1000){
        //         System.out.println(
        //             engine.getCountOfEvent(AccessLogEvent.class) + " - " +
        //             engine.getCountOfEvent(FailedLoginEvent.class) + " - " +
        //             engine.getCountOfEvent(UnauthorizedEvent.class) + " - " +
        //             engine.getCountOfEvent(ConsecutiveFailedLoginEvent.class) + " - " +
        //             engine.getCountOfEvent(BruteForceAttackEvent.class) + " - " +
        //             engine.getCountOfEvent(TcpPacketIncomingEvent.class) + " - " +
        //             engine.getCountOfEvent(SourceCountPortEvent.class) + " - " +
        //             engine.getCountOfEvent(PortCountSourceEvent.class) + " - " +
        //             engine.getCountOfEvent(BlockPortScanEvent.class) + " - "
        //         );
        //         prev = System.currentTimeMillis();
        //     }
        // }


    }
}
