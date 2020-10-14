package SIEMsystem;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.collector.EventCollector;
import SIEMsystem.collector.FailedLoginEvent;
import SIEMsystem.collector.LoginAlert;
import SIEMsystem.collector.UnauthorizedEvent;
import SIEMsystem.event.AccessLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // Setting up engine
        CEPEngine engine = new CEPEngine();
        engine.addEventType(AccessLog.class);
        engine.addEventType(FailedLoginEvent.class);
        engine.addEventType(UnauthorizedEvent.class);
        engine.addEventType(LoginAlert.class);
        engine.initRuntime();

        engine.compileAndDeploy("select-access-log", "select * from AccessLog;",
                AccessLog.class);
        // engine.compileAndDeploy("select-access-log-2", "select count(*) as count, sum(bytes) as sum from AccessLog;",
        //         AccessLog.class);

        engine.addListener((newData, oldData, stmt, rt) -> {
            /*System.out.println("IP: " + newData[0].get("ip"));
            System.out.println("User: " + newData[0].get("user"));
            System.out.println("Time: " + newData[0].get("time"));
            System.out.println("Method: " + newData[0].get("method"));
            System.out.println("URI: " + newData[0].get("uri"));
            System.out.println("Query: " + newData[0].get("query"));
            System.out.println("Protocol: " + newData[0].get("protocol"));
            System.out.println("Status: " + newData[0].get("status"));
            System.out.println("Bytes: " + newData[0].get("bytes"));
            System.out.println("Referer: " + newData[0].get("referer"));
            System.out.println("User agent: " + newData[0].get("useragent"));
            System.out.println();*/
        });

        engine.compileAndDeploy("select-failed-logins", "select * from AccessLog(status=\"401\");",
                AccessLog.class);
        engine.addListener((newData, oldData, stmt, rt) -> {
            AccessLog extractedEvent = (AccessLog) newData[0].getUnderlying();
            engine.getRuntime().getEventService().sendEventBean(new FailedLoginEvent(extractedEvent),
                    "FailedLoginEvent");
        });

        engine.compileAndDeploy("select-authorized-events", "select * from AccessLog(status=\"403\");",
                AccessLog.class);
        engine.addListener((newData, oldData, stmt, rt) -> {
            AccessLog extractedEvent = (AccessLog) newData[0].getUnderlying();
            engine.getRuntime().getEventService().sendEventBean(new UnauthorizedEvent(extractedEvent),
                    "UnauthorizedEvent");
        });

        String alertStatement = "select * from AccessLog " +
                "match_recognize ( " +
                "partition by ip " +
                "measures A as event1, B as event2 " +
                "pattern ((A B*){3}) " +
                "define " +
                "A as A.status = \"401\", " +
                "B as B.status != \"401\")";
        engine.compileAndDeploy("login-alert-event", alertStatement, AccessLog.class);
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("Found alert event.");
            System.out.println((String) oldData[0].get("event1.time"));
            List<String> parameterList = new ArrayList<>();
            parameterList.add((String) newData[0].get("event1.ip"));
            parameterList.add((String) newData[0].get("event1.user"));
            parameterList.add((String) newData[0].get("event1.time"));
            parameterList.add((String) newData[0].get("event1.method"));
            parameterList.add((String) newData[0].get("event1.uri"));
            parameterList.add((String) newData[0].get("event1.query"));
            parameterList.add((String) newData[0].get("event1.protocol"));
            parameterList.add((String) newData[0].get("event1.status"));
            parameterList.add((String) newData[0].get("event1.bytes"));
            parameterList.add((String) newData[0].get("event1.referer"));
            parameterList.add((String) newData[0].get("event1.useragent"));
            engine.getRuntime().getEventService().sendEventBean(new LoginAlert(parameterList), "LoginAlert");
        });
        // engine.addListener((newData, oldData, stmt, rt) -> {
        //     System.out.println("Count: " + newData[0].get("count") + ". Bytes: " + (int) newData[0].get("sum"));
        // });

        EventCollector collector = new EventCollector(engine.getRuntime());
        collector.collectAccessLog();
    }
}
