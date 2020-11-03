package SIEMsystem.event;
/**
 * Class for representing a block port scan event
 * @author Nguyen Dinh Thi
 */
public class BlockPortScanEvent {
  private String portScan;
  public void setPortScan(String scanType) {
    this.portScan = scanType;
  }
  public String getPortScan() {
    return this.portScan;
  }
}
