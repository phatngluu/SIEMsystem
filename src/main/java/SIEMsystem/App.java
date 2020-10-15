package SIEMsystem;

import SIEMsystem.AlertManager.AlertManager;
import SIEMsystem.cep.CEPEngine;
import SIEMsystem.collector.EventCollector;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        //Test update config
        AlertManager alertManager = new AlertManager();
        alertManager.setPriorities("FailedLoginEvent", "Low");


        // Setting up engine
        CEPEngine engine = new CEPEngine();

        EventCollector collector = new EventCollector(engine.getRuntime());
        collector.collectAccessLog();
    }
}
