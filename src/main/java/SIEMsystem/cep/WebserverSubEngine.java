package SIEMsystem.cep;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.BruteForceAttackAlert;
import SIEMsystem.alert.BruteForceAttackAlert2;
import SIEMsystem.alert.ConsecutiveFailLoginAlert;
import SIEMsystem.alert.FailLoginAlert;
import SIEMsystem.alert.UnauthorizedAlert;

public class WebserverSubEngine extends SubEngine {
    private static WebserverSubEngine instance;

    private WebserverSubEngine() {
    }

    public static WebserverSubEngine getInstance() {
        if (instance == null) {
            instance = new WebserverSubEngine();
            return instance;
        }
        return instance;
    }

    @Override
    protected void activate(CEPEngine engine) {
        int threshold = 3;
        int attackThreshold = 50;

        engine.compileAndDeploy(
                "insert into FailedLoginEvent " + "select ip, time, status " + "from AccessLogEvent(status=\"401\");");
        engine.compileAndDeploy("select ip, time, status from FailedLoginEvent")
                .addListener((newData, oldData, stmt, rt) -> {
                    // Failed login alert - LOW
                    String IP = (String) newData[0].get("ip");
                    String TIME = (String) newData[0].get("time");
                    String STATUS = (String) newData[0].get("status");
                    FailLoginAlert alert = new FailLoginAlert(IP, TIME, STATUS);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                });

        engine.compileAndDeploy(
                "insert into UnauthorizedEvent " + "select ip, time, status " + "from AccessLogEvent(status=\"403\");");
        engine.compileAndDeploy("select ip, time, status from UnauthorizedEvent")
                .addListener((newData, oldData, stmt, rt) -> {
                    // Unauth
                    String IP = (String) newData[0].get("ip");
                    String TIME = (String) newData[0].get("time");
                    String STATUS = (String) newData[0].get("status");
                    UnauthorizedAlert alert = new UnauthorizedAlert(IP, TIME, STATUS);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                });

        engine.compileAndDeploy("insert into LoginAlert " + "select ip, time, status, count(*) as count "
                + "from FailedLoginEvent#time(5 minutes) " + "group by ip " + "having count(*) >= " + threshold + ";");
        engine.compileAndDeploy("select count, ip, time from LoginAlert;").addListener((newData, oldData, stmt, rt) -> {
            // Consec
            String IP = (String) newData[0].get("ip");
            String TIME = (String) newData[0].get("time");
            Long COUNT = (Long) newData[0].get("count");
            ConsecutiveFailLoginAlert alert = new ConsecutiveFailLoginAlert(IP, TIME, COUNT);
            AlertManager am = AlertManager.getInstance();
            am.acceptAlert(alert);

        });

        engine.compileAndDeploy("insert into BruteForceAttackAlert " + "select time, count(*) as count "
                + "from FailedLoginEvent#time(5 minutes) " + "having count(*) >= " + attackThreshold + ";");

        engine.compileAndDeploy("select time, count from BruteForceAttackAlert;")
                .addListener((newData, oldData, stmt, rt) -> {
                    // Bruteforce
                    String TIME = (String) newData[0].get("time");
                    Long COUNT = (Long) newData[0].get("count");
                    BruteForceAttackAlert2 alert = new BruteForceAttackAlert2(TIME, COUNT);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);

                });
    }
}