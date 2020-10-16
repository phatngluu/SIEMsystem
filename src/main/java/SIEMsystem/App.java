package SIEMsystem;

import java.io.FileNotFoundException;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanSubEngine;
import SIEMsystem.cep.WebserverSubEngine;
import SIEMsystem.collector.EventCollector;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws FileNotFoundException {
        // Setting up engine
        CEPEngine engine = new CEPEngine();
        engine.activate(WebserverSubEngine.getInstance());
        engine.activate(PortscanSubEngine.getInstance());

        EventCollector collector = new EventCollector(engine.getRuntime());
        // collector.collectAccessLog();
        collector.collectLog();
    }
}
