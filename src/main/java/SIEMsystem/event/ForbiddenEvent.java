package SIEMsystem.event;

/**
 * This class represents for an event happens whenever a web server's user access restricted resouces.
 * @author Luu Nguyen Phat
 */
public class ForbiddenEvent extends AccessLogEvent {
    public ForbiddenEvent(AccessLogEvent al) {
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