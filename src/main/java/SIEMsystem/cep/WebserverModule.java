package SIEMsystem.cep;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.BruteForceAttackAlert;
import SIEMsystem.alert.ConsecutiveFailedLoginAlert;
import SIEMsystem.alert.FailedLoginAlert;
import SIEMsystem.alert.ForbiddenAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.BruteForceAttackEvent;
import SIEMsystem.event.ConsecutiveFailedLoginEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.ForbiddenEvent;
/**
 * Class for running the web server monitoring module
 * @author Luu Nguyen Phat
 * @author Nguyen Dinh Thi
 */
public class WebserverModule extends Module {
    private static WebserverModule instance;

    private WebserverModule() {
    }
    /**
     * Method that returns an instance of this class (WebserverModule)
     * @return
     *  Returns an instance of the class WebserverModule
     */
    public static WebserverModule getInstance() {
        if (instance == null) {
            instance = new WebserverModule();
            return instance;
        }
        return instance;
    }
    /**
     * Method that compiles and deploys the EPL statements for the web server monitoring module
     * @param engine
     *  The CEP engine used in this module
     */
    @Override
    protected void activate(CEPEngine engine) {

        engine.compileAndDeploy("select * from AccessLogEvent;").addListener(
            (newData, __, ___, ____) -> {
                engine.countEvent(AccessLogEvent.class);
            }
        );
        // FailedLoginEvent
        engine.compileAndDeploy(
                "insert into FailedLoginEvent " + "select ip, time, status " + "from AccessLogEvent(status=\"401\");");
        engine.compileAndDeploy("select ip, time, status from FailedLoginEvent;")
                .addListener((newData, oldData, stmt, rt) -> {
                    String IP = (String) newData[0].get("ip");
                    String TIME = (String) newData[0].get("time");
                    String STATUS = (String) newData[0].get("status");
                    FailedLoginAlert alert = new FailedLoginAlert(IP, TIME, STATUS);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                    engine.countEvent(FailedLoginEvent.class);
                });
        // ForbiddenEvent
        engine.compileAndDeploy(
                "insert into ForbiddenEvent " + "select ip, time, status " + "from AccessLogEvent(status=\"403\");");
        engine.compileAndDeploy("select ip, time, status from ForbiddenEvent;")
                .addListener((newData, oldData, stmt, rt) -> {
                    String IP = (String) newData[0].get("ip");
                    String TIME = (String) newData[0].get("time");
                    String STATUS = (String) newData[0].get("status");
                    ForbiddenAlert alert = new ForbiddenAlert(IP, TIME, STATUS);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                    engine.countEvent(ForbiddenEvent.class);
                });
        // ConsecutiveFailedLoginEvent
        engine.compileAndDeploy("insert into ConsecutiveFailedLoginEvent "
                + "select ip, time, status, count(*) as count " + "from FailedLoginEvent#time(" + engine.getProperty("WEBSERVER_CONSECUTIVE_FAILEDLOGIN_TIME_WINDOW_IN_SECONDS") + ") "
                + "group by ip " + "having count(*) >= " + engine.getProperty("WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD") + ";");
        engine.compileAndDeploy("select count, ip, time from ConsecutiveFailedLoginEvent;")
                .addListener((newData, oldData, stmt, rt) -> {
                    String IP = (String) newData[0].get("ip");
                    String TIME = (String) newData[0].get("time");
                    Long COUNT = (Long) newData[0].get("count");
                    int TIMEWINDOW = Integer.valueOf(engine.getProperty("WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD"));
                    ConsecutiveFailedLoginAlert alert = new ConsecutiveFailedLoginAlert(IP, TIME, COUNT, TIMEWINDOW);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                    engine.countEvent(ConsecutiveFailedLoginEvent.class);
                });
        // BruteForceAttackEvent
        engine.compileAndDeploy("insert into BruteForceAttackEvent " + "select time, count(*) as count "
                + "from FailedLoginEvent#time(" + engine.getProperty("WEBSERVER_BRUTE_FORCE_TIME_WINDOW_IN_SECONDS") + ") " + "having count(*) >= " + engine.getProperty("WEBSERVER_BRUTEFORCE_LOWER_THRESHOLD") + ";");
        engine.compileAndDeploy("select time, count from BruteForceAttackEvent;")
                .addListener((newData, oldData, stmt, rt) -> {
                    String TIME = (String) newData[0].get("time");
                    Long COUNT = (Long) newData[0].get("count");
                    int TIMEWINDOW = Integer.valueOf(engine.getProperty("WEBSERVER_BRUTE_FORCE_TIME_WINDOW_IN_SECONDS"));
                    BruteForceAttackAlert alert = new BruteForceAttackAlert(TIME, COUNT, TIMEWINDOW);
                    AlertManager am = AlertManager.getInstance();
                    am.acceptAlert(alert);
                    engine.countEvent(BruteForceAttackEvent.class);
                });
    }
}