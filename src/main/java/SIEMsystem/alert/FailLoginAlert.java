package SIEMsystem.alert;

public class FailLoginAlert extends Alert {
    public FailLoginAlert(String ip, String time, String status) {
        super();
        this.setMessage("WARNING : Failed login attempt\n" +
            "IP : " + ip + "\n" +
            "Time : " + time + "\n" +
            "Status : " + status);
    }
}
