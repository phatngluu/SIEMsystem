package SIEMsystem.event;
import lombok.Getter;
import lombok.Setter;

public class BruteForceAttackEvent {
    @Getter @Setter private String time;
    @Getter @Setter private Long count;
}
