package SIEMsystem.event;

import lombok.Getter;
import lombok.Setter;
import nl.basjes.parse.core.Field;

public class AccessLog{
    @Getter @Setter(onMethod=@__(@Field("IP:connection.client.host"))) private String ip;
    @Getter @Setter(onMethod=@__(@Field("STRING:connection.client.user"))) private String user;
    @Getter @Setter(onMethod=@__(@Field("TIME.STAMP:request.receive.time"))) private String time;
    @Getter @Setter(onMethod=@__(@Field("HTTP.METHOD:request.firstline.method"))) private String method;
    @Getter @Setter(onMethod=@__(@Field("HTTP.URI:request.firstline.uri"))) private String uri;
    @Getter @Setter(onMethod=@__(@Field("HTTP.QUERYSTRING:request.firstline.uri.query"))) private String query;
    @Getter @Setter(onMethod=@__(@Field("HTTP.PROTOCOL:request.firstline.protocol"))) private String protocol;
    @Getter @Setter(onMethod=@__(@Field("STRING:request.status.last"))) private String status;
    @Getter @Setter(onMethod=@__(@Field("BYTESCLF:response.body.bytes"))) private String bytes;
    @Getter @Setter(onMethod=@__(@Field("HTTP.URI:request.referer"))) private String referer;
    @Getter @Setter(onMethod=@__(@Field("HTTP.USERAGENT:request.user-agent"))) private String useragent;
}
