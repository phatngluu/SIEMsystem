package SIEMsystem.dashboard;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.event.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Counting {
    @FXML
    private TableView<EventCount> counttable;
    @FXML
    private TableColumn<EventCount, String> name;
    @FXML
    private TableColumn<EventCount, Long> num;

    private ObservableList<EventCount> masterData = FXCollections.observableArrayList();

    public void update1() {
        masterData.clear();
        masterData.add(
                new EventCount("AccessLogEvent", CEPEngine.getCreatedInstance().getCountOfEvent(AccessLogEvent.class)));
        masterData.add(new EventCount("BlockPortScanEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(BlockPortScanEvent.class)));
        masterData.add(new EventCount("BruteForceAttackEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(BruteForceAttackEvent.class)));
        masterData.add(new EventCount("ClosedPortScanEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(ClosedPortScanEvent.class)));
        masterData.add(new EventCount("ConsecutiveFailedLoginEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(ConsecutiveFailedLoginEvent.class)));
        masterData.add(new EventCount("FailedLoginEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(FailedLoginEvent.class)));
        masterData.add(
                new EventCount("ForbiddenEvent", CEPEngine.getCreatedInstance().getCountOfEvent(ForbiddenEvent.class)));
        masterData.add(new EventCount("HighCPUUsageEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(HighCPUUsageEvent.class)));
        masterData.add(new EventCount("HighMemoryUsageEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(HighMemoryUsageEvent.class)));
        masterData.add(new EventCount("OpenPortScanEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(OpenPortScanEvent.class)));
        masterData.add(new EventCount("HorizontalPortscanEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(HorizontalPortscanEvent.class)));
        masterData.add(
                new EventCount("PortScanEvent", CEPEngine.getCreatedInstance().getCountOfEvent(PortScanEvent.class)));
        masterData.add(new EventCount("ResourceMonitorEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(ResourceMonitorEvent.class)));
        masterData.add(new EventCount("VerticalPortscanEvent",
                CEPEngine.getCreatedInstance().getCountOfEvent(VerticalPortscanEvent.class)));
        masterData.add(
                new EventCount("TcpPacketEvent", CEPEngine.getCreatedInstance().getCountOfEvent(TcpPacketEvent.class)));
    }

    public Counting() {
        update1();
    }

    Thread thread = new Thread() {
        public void run() {
            while (true) {
                update1();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };

    @FXML
    private void initialize() {
        name.setCellValueFactory(new PropertyValueFactory<EventCount, String>("name"));
        num.setCellValueFactory(new PropertyValueFactory<EventCount, Long>("num"));

        FilteredList<EventCount> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<EventCount> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(counttable.comparatorProperty());
        counttable.setItems(masterData);
        // this.update();
        thread.start();

    }
    
}
