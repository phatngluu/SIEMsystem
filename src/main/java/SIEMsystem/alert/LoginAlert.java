package SIEMsystem.alert;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class LoginAlert {
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

    public LoginAlert(List<String> para) {
        this.ip = para.get(0);
        this.user = para.get(1);
        this.time = para.get(2);
        this.method = para.get(3);
        this.uri = para.get(4);
        this.query = para.get(5);
        this.protocol = para.get(6);
        this.status = para.get(7);
        this.bytes = para.get(8);
        this.referer = para.get(9);
        this.useragent = para.get(10);
        System.out.println("LoginAlert created");
        System.out.println("IP: " + this.getIp());
        System.out.println("User: " + this.getUser());
        System.out.println("Time: " + this.getTime());
        System.out.println("Method: " + this.getMethod());
        System.out.println("URI: " + this.getUri());
        System.out.println("Query: " + this.getQuery());
        System.out.println("Protocol: " + this.getProtocol());
        System.out.println("Status: " + this.getStatus());
        System.out.println("Bytes: " + this.getBytes());
        System.out.println("Referer: " + this.getReferer());
        System.out.println("User agent: " + this.getUseragent());
        System.out.println();
    }
}