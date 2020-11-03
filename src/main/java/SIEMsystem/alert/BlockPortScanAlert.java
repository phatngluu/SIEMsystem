package SIEMsystem.alert;
/**
 * Class for reprensenting a block port scan alert
 * @author Nguyen Dinh Thi
 * @version 1.0
 */
public class BlockPortScanAlert extends Alert {
  public BlockPortScanAlert() {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage("Block port scan detected.");
  }
}
