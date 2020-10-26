package SIEMsystem.alert;

public class ForbiddenAlert extends Alert {

    public ForbiddenAlert(String ip, String time, String status) {
        // super();
        // this.setMessage("WARNING : Unauthorized access\n" +
        //     "IP : " + ip + "\n" +
        //     "Time : " + time + "\n" +
        //     "Status : " + status);
        super();
        this.name = this.getClass().getSimpleName();
        this.setMessage(ip + " access to restricted resource detected.");
    }
}