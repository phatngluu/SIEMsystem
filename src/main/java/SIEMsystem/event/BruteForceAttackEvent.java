package SIEMsystem.event;
import lombok.Getter;
import lombok.Setter;
/**
 * Class for representing a brute force attack event
 * @author Nguyen Dinh Thi
 */
public class BruteForceAttackEvent {
    @Getter @Setter private String time;
    @Getter @Setter private Long count;
}
