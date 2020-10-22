package SIEMsystem.sample;

import java.util.Date;

public class Alert {
    private String name;
    private Date timestamp;
    private String message;
    private String priority;
    public Alert(String name){
        this.name = name;
        this.timestamp = new Date();
        this.priority = "Low";
        this.message = "This alert message comes from abstract alert.";
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
