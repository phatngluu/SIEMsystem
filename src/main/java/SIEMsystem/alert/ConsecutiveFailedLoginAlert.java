package SIEMsystem.alert;
/**
 * Class for representing the alert raised when a user logs in unsuccessfully for multiple times consecutively.
 * @author Nguyen Dinh Thi
 */
public class ConsecutiveFailedLoginAlert extends Alert {
    /**
     * Constructor of the class
     * @param ip
     *  The IP address of the person who logs in unsuccessfully
     * @param time
     *  Time of the login attempt
     * @param count
     *  Number of consecutive failed login attempt within the time window
     * @param timeWindow
     *  The period of time considered (in seconds) to raise the alert
     */
    public ConsecutiveFailedLoginAlert(String ip, String time, Long count, int timeWindow) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage(count + " failed login attempts from " + ip + " within " + timeWindow + " seconds detected.");
    }
}
