package SIEMsystem.alert;

public class ConsecutiveFailedLoginAlert extends Alert {

    public ConsecutiveFailedLoginAlert(String ip, String time, Long count) {
        // super();
        // StringBuilder sb = new StringBuilder();
        // sb.append("ALERT : " + count + " failed login attempts within 5 minutes detected\n");
        // sb.append("IP : " + ip + "\n");
        // sb.append("Time : " + time + "\n");
        // this.setMessage(sb.toString());
        super();
        this.setMessage("At " + time + ": " + count + " failed login attempts from " + ip + " within 5 minutes detected.");
    }
}
