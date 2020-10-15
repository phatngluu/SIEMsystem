package SIEMsystem.alert;

public class ConsecutiveFailLoginAlert extends Alert {

    public ConsecutiveFailLoginAlert(String ip, String time, Long count) {
        super();
        StringBuilder sb = new StringBuilder();
        sb.append("ALERT : " + count + " failed login attempts within 5 minutes detected\n");
        sb.append("IP : " + ip + "\n");
        sb.append("Time : " + time + "\n");
        this.setMessage(sb.toString());
    }

}
