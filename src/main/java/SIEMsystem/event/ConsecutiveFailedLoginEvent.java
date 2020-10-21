package SIEMsystem.event;

import lombok.Getter;
import lombok.Setter;

public class ConsecutiveFailedLoginEvent {
    @Getter @Setter private String ip;
    @Getter @Setter private String time;
    @Getter @Setter private String status;
    @Getter @Setter private long count;
}