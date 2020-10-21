package SIEMsystem.alert;

public class UnauthorizedAlert extends Alert {

    public UnauthorizedAlert(String ip, String time, String status) {
        // super();
        // this.setMessage("WARNING : Unauthorized access\n" +
        //     "IP : " + ip + "\n" +
        //     "Time : " + time + "\n" +
        //     "Status : " + status);
        super();
        this.setMessage("At " + time + ": " + ip + " access to restricted resource detected.");
    }
}