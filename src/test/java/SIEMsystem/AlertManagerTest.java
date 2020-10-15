package SIEMsystem;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import SIEMsystem.alert.AlertManager;
import SIEMsystem.alert.ConsecutiveFailLoginAlert;
import SIEMsystem.alert.FailLoginAlert;
import SIEMsystem.alert.UnauthorizedAlert;
public class AlertManagerTest {
    @Test
    public void testAlertManager()
    {
        //Test alert manager
        AlertManager alertManager = AlertManager.getInstance();
        System.out.println(
        alertManager.acceptAlert(new UnauthorizedAlert()).getPriority() + 
        alertManager.acceptAlert(new FailLoginAlert()).getPriority() + 
        alertManager.acceptAlert(new ConsecutiveFailLoginAlert()).getPriority()
        );

        alertManager.setPriority(UnauthorizedAlert.class, "Medium");
        alertManager.setPriority(FailLoginAlert.class, "Medium");
        alertManager.setPriority(ConsecutiveFailLoginAlert.class, "Medium");

        System.out.println(
        alertManager.acceptAlert(new UnauthorizedAlert()).getPriority() + 
        alertManager.acceptAlert(new FailLoginAlert()).getPriority() + 
        alertManager.acceptAlert(new ConsecutiveFailLoginAlert()).getPriority()
        );

        System.out.println(
        new UnauthorizedAlert().getPriority() + 
        new FailLoginAlert().getPriority() + 
        new ConsecutiveFailLoginAlert().getPriority()
        );

        assertTrue(true);
    }
}
