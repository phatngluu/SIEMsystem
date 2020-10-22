package SIEMsystem.event;

public class BlockPortScanEvent {
  private String portScan;
  public void setPortScan(String scanType) {
    this.portScan = scanType;
  }
  public String getPortScan() {
    return this.portScan;
  }
}
