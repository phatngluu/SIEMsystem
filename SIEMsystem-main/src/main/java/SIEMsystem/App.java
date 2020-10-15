package SIEMsystem;

import SIEMsystem.alert.BruteForceAttackAlert;
import SIEMsystem.cep.CEPEngine;
import SIEMsystem.collector.EventCollector;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.UnauthorizedEvent;
import SIEMsystem.event.AccessLogEvent;


public class App {
    public static void main(String[] args) {
        AlertPriorities alertPriorities = new AlertPriorities();
        alertPriorities.setProperties();
        int threshold = 3;
        int attackThreshold = 50;

        // Setting up engine
        CEPEngine engine = new CEPEngine();
        engine.addEventType(AccessLogEvent.class);
        engine.addEventType(FailedLoginEvent.class);
        engine.addEventType(UnauthorizedEvent.class);
        engine.addEventType(LoginAlert.class);
        engine.addEventType(BruteForceAttackAlert.class);
        engine.initRuntime();

        engine.compileAndDeploy("insert-failed-logins",
            "insert into FailedLoginEvent " +
                "select ip, time, status, \"" + alertPriorities.getFailedLogin() + "\" as priority " +
                "from AccessLogEvent(status=\"401\");");
        engine.compileAndDeploy("display-failed-logins",
            "select ip, time, status, priority from FailedLoginEvent");
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("WARNING : Failed login attempt");
            System.out.println("IP : " + newData[0].get("ip"));
            System.out.println("Time : " + newData[0].get("time"));
            System.out.println("Status : " + newData[0].get("status"));
            System.out.println("Priority : " + newData[0].get("priority"));
            System.out.println();
        });

        engine.compileAndDeploy("insert-unauthorized-events",
            "insert into UnauthorizedEvent " +
                "select ip, time, status, \"" + alertPriorities.getUnauthLogin() + "\" as priority " +
                "from AccessLogEvent(status=\"403\");");
        engine.compileAndDeploy("display-unauthorized-events",
            "select ip, time, status, priority " +
                "from UnauthorizedEvent");
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("WARNING : Unauthorized access");
            System.out.println("IP : " + newData[0].get("ip"));
            System.out.println("Time : " + newData[0].get("time"));
            System.out.println("Status : " + newData[0].get("status"));
            System.out.println("Priority : " + newData[0].get("priority"));
            System.out.println();
        });

        engine.compileAndDeploy("insert-login-alert-events",
            "insert into LoginAlert " +
                "select ip, time, status, count(*) as count, \"" + alertPriorities.getFailed5Login() + "\" as priority " +
                "from FailedLoginEvent#time(5 minutes) " +
                "group by ip " +
                "having count(*) >= " + threshold + ";");
        engine.compileAndDeploy("display-alert",
            "select count, ip, time, priority from LoginAlert;");
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("ALERT : " + newData[0].get("count") + " failed login attempts within 5 minutes detected");
            System.out.println("IP : " + newData[0].get("ip"));
            System.out.println("Time : " + newData[0].get("time"));
            System.out.println("Priority : " + newData[0].get("priority"));
            System.out.println();
        });

        engine.compileAndDeploy("insert-attack-events",
            "insert into BruteForceAttackAlert " +
                "select time, count(*) as count, \"" + alertPriorities.getBruteForceAttack() + "\" as priority " +
                "from FailedLoginEvent#time(5 minutes) " +
                "having count(*) >= " + attackThreshold + ";");
        engine.compileAndDeploy("display-attack-alert",
            "select time, count, priority from BruteForceAttackAlert;");
        engine.addListener((newData, oldData, stmt, rt) -> {
            System.out.println("ALERT : " + newData[0].get("count") + " failed login attempts within 5 minutes detected");
            System.out.println("POTENTIAL ATTACK");
            System.out.println("Time : " + newData[0].get("time"));
            System.out.println("Priority : " + newData[0].get("priority"));
            System.out.println();
        });

        EventCollector collector = new EventCollector(engine.getRuntime());
        collector.collectAccessLog();
    }
}
