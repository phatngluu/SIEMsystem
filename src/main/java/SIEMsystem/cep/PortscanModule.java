package SIEMsystem.cep;

import java.util.ArrayList;
import java.util.List;

import SIEMsystem.alert.LoginAlert;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.UnauthorizedEvent;

public class PortscanModule extends Module {
    private static PortscanModule instance;
        private PortscanModule(){}
        public static PortscanModule getInstance(){
            if (instance == null){
                    instance = new PortscanModule();
                    return instance;
            }
            return instance;
        }

    @Override
    protected void activate(CEPEngine engine) {
        
    }
}
