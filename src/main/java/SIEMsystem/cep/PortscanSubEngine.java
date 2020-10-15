package SIEMsystem.cep;

import java.util.ArrayList;
import java.util.List;

import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;

public class PortscanSubEngine {
    public static void activate(CEPEngine engine) {
        engine.compileAndDeploy("select * from AccessLogEvent;");

        engine.compileAndDeploy("select * from AccessLogEvent(status=\"401\");")
                .addListener((newData, oldData, stmt, rt) -> {
                    AccessLogEvent extractedEvent = (AccessLogEvent) newData[0].getUnderlying();
                    engine.getRuntime().getEventService().sendEventBean(new FailedLoginEvent(extractedEvent),
                            "FailedLoginEvent");
                });

        engine.compileAndDeploy("select * from AccessLogEvent(status=\"403\");")
                .addListener((newData, oldData, stmt, rt) -> {
                    AccessLogEvent extractedEvent = (AccessLogEvent) newData[0].getUnderlying();
                    engine.getRuntime().getEventService().sendEventBean(new UnauthorizedEvent(extractedEvent),
                            "UnauthorizedEvent");
                });

        String alertStatement = "select * from AccessLogEvent " + "match_recognize ( " + "partition by ip "
                + "measures A as event1, B as event2 " + "pattern ((A B*){3}) " + "define "
                + "A as A.status = \"401\", " + "B as B.status != \"401\")";
        engine.compileAndDeploy(alertStatement).addListener((newData, oldData, stmt, rt) -> {
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
    }
}
