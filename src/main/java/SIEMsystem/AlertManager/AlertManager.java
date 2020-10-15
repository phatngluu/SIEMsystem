package SIEMsystem.AlertManager;

import java.io.*;
import java.util.Properties;

public class AlertManager{
    protected Properties properties = new Properties();
    static File configFile = new File(".resources/properties/config.properties");

    public AlertManager() {
        try {
            FileReader reader = new FileReader(configFile);
            properties.load(reader);
            reader.close();
        } catch (IOException ignore) {
        }
    }

    public String AlertWithProperty(String alertName) {
        String value = properties.getProperty(alertName);
        return value;
    }

    public String acceptAlert(String alertName) {
        return AlertWithProperty(alertName);
    }



    public void setPriorities(String alertName, String newPriority) {
        // Getting properties
        try {
            FileInputStream in = new FileInputStream(configFile);
            Properties props = new Properties();
            props.load(in);
            in.close();

            FileOutputStream out = new FileOutputStream(configFile);
            props.setProperty(alertName, newPriority);
            props.store(out, null);
            out.close();
        } catch (IOException ignore) {
        }
    }
}