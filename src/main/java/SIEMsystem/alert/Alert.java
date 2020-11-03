package SIEMsystem.alert;

import java.util.Date;


/**
 * This is an abstract class for all alerts of the system
 * @author Luu Nguyen Phat
 */
public abstract class Alert {
    protected String name;
    private Date timestamp;
    private String message;
    private String priority;
    public Alert(){
        this.name = this.getClass().getSimpleName();
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
