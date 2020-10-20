package SIEMsystem.alert;

public class FailedLoginAlert extends Alert {
    public FailedLoginAlert(String ip, String time, String status) {
        // super();
        // this.setMessage("WARNING : Failed login attempt\n" +
        //     "IP : " + ip + "\n" +
        //     "Time : " + time + "\n" +
            // "Status : " + status);
        super();
        this.setMessage("At " + time + ": A failed login attempt from " + ip + " within 5 minutes detected.");
    }
}
