package SIEMsystem.alert;

public class BlockPortScanAlert extends Alert {
  public BlockPortScanAlert() {
    super();
    this.name = this.getClass().getSimpleName();
    this.setMessage("Block port scan detected.");
  }
}
