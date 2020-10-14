package SIEMsystem.AlertManager;

import java.io.*;
import java.util.Properties;

public class AlertManager{
    static File configFile = new File(".resources/properties/config.properties");

    public static String getPriorities(String value) {
        // Getting properties
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String result = props.getProperty(value);

            reader.close();
            return result;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } return null;
    }



    public void setPriorities() {
        // Getting properties
        try {
            Properties props = new Properties();
            props.setProperty("FailedLogin", "Medium");
            props.setProperty("Failed3Login", "High");
            props.setProperty("FailedAuth", "High");
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Alert Priorities");
            writer.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
}