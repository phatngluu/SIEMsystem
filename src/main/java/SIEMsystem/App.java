package SIEMsystem;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.collector.EventCollector;
import SIEMsystem.event.AccessLog;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // Setting up engine
        CEPEngine engine = new CEPEngine();
        engine.addEventType(AccessLog.class);
        engine.initRuntime();

        engine.compileAndDeploy("select-access-log", "select * from AccessLog;",
                AccessLog.class);
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("Bytes: " + newData[0].get("bytes"));
        });

        EventCollector collector = new EventCollector(engine.getRuntime());
        collector.collectAccessLog();
    }
}
