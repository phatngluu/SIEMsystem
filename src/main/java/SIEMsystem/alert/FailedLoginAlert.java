package SIEMsystem.alert;

public class FailedLoginAlert extends Alert {
    public FailedLoginAlert(String ip, String time, String status) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("A failed login attempt from " + ip + " detected.");
    }
}
