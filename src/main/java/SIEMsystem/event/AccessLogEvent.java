package SIEMsystem.event;

import lombok.Getter;
import lombok.Setter;
import nl.basjes.parse.core.Field;

/**
 * This class represents for raw event that comes from web server log file.
 * @author Luu Nguyen Phat
 */
public class AccessLogEvent{
    @Getter @Setter(onMethod=@__(@Field("IP:connection.client.host"))) protected String ip;
    @Getter @Setter(onMethod=@__(@Field("STRING:connection.client.user"))) protected String user;
    @Getter @Setter(onMethod=@__(@Field("TIME.STAMP:request.receive.time"))) protected String time;
    @Getter @Setter(onMethod=@__(@Field("HTTP.METHOD:request.firstline.method"))) protected String method;
    @Getter @Setter(onMethod=@__(@Field("HTTP.URI:request.firstline.uri"))) protected String uri;
    @Getter @Setter(onMethod=@__(@Field("HTTP.QUERYSTRING:request.firstline.uri.query"))) protected String query;
    @Getter @Setter(onMethod=@__(@Field("HTTP.PROTOCOL:request.firstline.protocol"))) protected String protocol;
    @Getter @Setter(onMethod=@__(@Field("STRING:request.status.last"))) protected String status;
    @Getter @Setter(onMethod=@__(@Field("BYTESCLF:response.body.bytes"))) protected String bytes;
    @Getter @Setter(onMethod=@__(@Field("HTTP.URI:request.referer"))) protected String referer;
    @Getter @Setter(onMethod=@__(@Field("HTTP.USERAGENT:request.user-agent"))) protected String useragent;
}
