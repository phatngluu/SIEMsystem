package SIEMsystem;

import java.io.*;
import java.util.Properties;

public class AlertPriorities {
    File configFile = new File(".resources/properties/config.properties");
    public String getSuccessLogin() {
        // Getting properties
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String value = props.getProperty("SuccessLogin");

            reader.close();
            return value;
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        } return null;
    }

    public String getFailedLogin() {
        // Getting properties
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String value = props.getProperty("FailedLogin");

            reader.close();
            return value;
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        } return null;
    }

    public String getFailed5Login() {
        // Getting properties
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String value = props.getProperty("Failed5Login");

            reader.close();
            return value;
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        } return null;
    }

    public String getUnauthLogin() {
        // Getting properties
        try {
            FileReader reader = new FileReader(configFile);
            Properties props = new Properties();
            props.load(reader);

            String value = props.getProperty("UnauthLogin");

            reader.close();
            return value;
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        } return null;
    }

    // Writing to config
    public void setProperties() {
        // Getting properties
        try {
            Properties props = new Properties();
            props.setProperty("SuccessLogin", "Low");
            props.setProperty("FailedLogin", "Medium");
            props.setProperty("Failed5Login", "High");
            props.setProperty("UnauthLogin", "High");
            FileWriter writer = new FileWriter(configFile);
            props.store(writer, "Alert Priorities");
            writer.close();
        } catch (FileNotFoundException ex) {
            // file does not exist
        } catch (IOException ex) {
            // I/O error
        }
    }
}