package SIEMsystem.alert;

public class ConsecutiveFailedLoginAlert extends Alert {

    public ConsecutiveFailedLoginAlert(String ip, String time, Long count) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage(count + " failed login attempts from " + ip + " within 5 minutes detected.");
    }
}
