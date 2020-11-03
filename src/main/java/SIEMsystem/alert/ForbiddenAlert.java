package SIEMsystem.alert;
/**
 * Class for representing the alert raised when a user accesses a forbidden page on the server
 * @author Nguyen Dinh Thi
 */
public class ForbiddenAlert extends Alert {
    /**
     * Constructor of the class
     * @param ip
     *  The IP address of the person who logs in unsuccessfully
     * @param time
     *  Time of the login attempt
     * @param status
     *  The HTTP status code of the event (403)
     */
    public ForbiddenAlert(String ip, String time, String status) {
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage(ip + " access to restricted resource detected.");
    }
}