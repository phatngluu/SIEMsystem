package SIEMsystem;

import java.io.FileNotFoundException;

import com.espertech.esper.common.client.configuration.Configuration;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.WebserverModule;
import SIEMsystem.collector.EventCollector;

import SIEMsystem.alert.BruteForceAttackAlert;
import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        // Setting up engine
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(AccessLogEvent.class);
        configuration.getCommon().addEventType(FailedLoginEvent.class);
        configuration.getCommon().addEventType(UnauthorizedEvent.class);
        configuration.getCommon().addEventType(LoginAlert.class);
        configuration.getCommon().addEventType(BruteForceAttackAlert.class);
        
        CEPEngine engine = new CEPEngine(configuration);
        engine.activate(WebserverModule.getInstance());
        engine.activate(PortscanModule.getInstance());

        EventCollector collector = new EventCollector(engine.getRuntime());
        // collector.collectAccessLog();
        collector.collectLog();
    }
}
