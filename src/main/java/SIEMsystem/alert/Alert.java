package SIEMsystem.alert;

import java.util.Date;

public abstract class Alert {
    private Date timestamp;
    private String message;
    private String priority;
    public Alert(){
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
}
