package SIEMsystem.event;

/**
 * This class represents for an event happens whenever a web server's user failed to authenticate him-/herself.
 * @author Luu Nguyen Phat
 */
public class FailedLoginEvent extends AccessLogEvent {
    public FailedLoginEvent(AccessLogEvent al) {
        this.ip = al.getIp();
        this.user = al.getUser();
        this.time = al.getTime();
        this.method = al.getMethod();
        this.uri = al.getUri();
        this.query = al.getQuery();
        this.protocol = al.getProtocol();
        this.status = al.getStatus();
        this.bytes = al.getBytes();
        this.referer = al.getReferer();
        this.useragent = al.getUseragent();
    }
}