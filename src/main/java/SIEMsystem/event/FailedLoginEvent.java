package SIEMsystem.event;

import SIEMsystem.AlertManager.*;
import lombok.Getter;
import lombok.Setter;

public class FailedLoginEvent {
    @Getter @Setter private String ip;
    @Getter @Setter private String user;
    @Getter @Setter private String time;
    @Getter @Setter private String method;
    @Getter @Setter private String uri;
    @Getter @Setter private String query;
    @Getter @Setter private String protocol;
    @Getter @Setter private String status;
    @Getter @Setter private String bytes;
    @Getter @Setter private String referer;
    @Getter @Setter private String useragent;

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
        System.out.println("FailedLoginEvent created");
    }
}