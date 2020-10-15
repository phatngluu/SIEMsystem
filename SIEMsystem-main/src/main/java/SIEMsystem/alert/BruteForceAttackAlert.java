package SIEMsystem.alert;
import lombok.Getter;
import lombok.Setter;

public class BruteForceAttackAlert {
    @Getter @Setter private String time;
    @Getter @Setter private Long count;
    @Getter @Setter private String priority;
}
