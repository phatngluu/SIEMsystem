package SIEMsystem.alert;

import java.io.*;
import java.util.Properties;

import SIEMsystem.dashboard.Dashboard;
/**
 * This class is about attaching priority and forwarding alert to dashboard.
 * @author Nguyen T. Nguyen
 */
@SuppressWarnings("rawtypes")
public class AlertManager {
    private static AlertManager instance;
    private Properties properties = new Properties();
    private File configFile = new File(".resources/properties/priorities.properties");

    /**
     *
     * @return return single instance of alert manager
     */
    public static AlertManager getInstance(){
        if (instance == null) {
            instance = new AlertManager();
        }
        return instance;
    }

    private AlertManager() {
        try {
            FileReader reader = new FileReader(configFile);
            properties.load(reader);
            reader.close();
        } catch (IOException ignore) {
        }
    }

    /**
     * this method attachs the alert with its property and forward to dashboard.
     * @param alert is an instance of specific alert
     * @return an alert with its attached priority
     */
    public Alert acceptAlert(Alert alert) {
        alert.setPriority(this.properties.getProperty(alert.getClass().getSimpleName()));
        System.out.println(alert.getMessage());
        Dashboard.acceptAlert(alert);
        return alert;
    }

    /**
     *
     * @param alertClass is a specific alert class (not an instance). Eg: UnauthorizedAlert
     * @param newPriority can be "Low", "Medium", "High".
     */
    public void setPriority(Class alertClass, String newPriority) {
        try {
            FileOutputStream out = new FileOutputStream(configFile);
            properties.setProperty(alertClass.getSimpleName(), newPriority);
            properties.store(out, null);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(Class alertClass){
        return this.properties.getProperty(alertClass.getSimpleName());
    }
}