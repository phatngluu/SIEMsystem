package SIEMsystem.cep;

import java.util.ArrayList;
import java.util.List;

import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;

public class PortscanSubEngine extends SubEngine {
    private static PortscanSubEngine instance;
        private PortscanSubEngine(){}
        public static PortscanSubEngine getInstance(){
            if (instance == null){
                    instance = new PortscanSubEngine();
                    return instance;
            }
            return instance;
        }

    @Override
    protected void activate(CEPEngine engine) {
        
    }
}
