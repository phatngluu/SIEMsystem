package SIEMsystem.alert;

public abstract class Alert {
    private String priority;
    private String message;
    public Alert(){
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
    
}
