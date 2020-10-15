package SIEMsystem;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.collector.EventCollector;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        AlertPriorities alertPriorities = new AlertPriorities();
        alertPriorities.setProperties();

        // Setting up engine
        CEPEngine engine = new CEPEngine();

        EventCollector collector = new EventCollector(engine.getRuntime());
        collector.collectAccessLog();
    }
}
