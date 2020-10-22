package SIEMsystem.alert;

public class BlockPortScanAlert extends Alert {
  public BlockPortScanAlert() {
    super();
    this.setMessage("Block port scan detected.");
  }
}
