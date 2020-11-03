package SIEMsystem.alert;
/**
 * Class for reprensenting the alert raised when a user logs in unsuccessfully
 * @author Nguyen Dinh Thi
 */
public class FailedLoginAlert extends Alert {
    /**
     * Constructor of the class
     * @param ip
     *  The IP address of the person who logs in unsuccessfully
     * @param time
     *  Time of the login attempt
     * @param status
     *  The HTTP status code of the event (401)
     */
    public FailedLoginAlert(String ip, String time, String status) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage("A failed login attempt from " + ip + " detected.");
    }
}
