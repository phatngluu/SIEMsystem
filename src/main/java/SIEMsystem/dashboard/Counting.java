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



public class Counting{
    @FXML
    private TableView<EventCount> counttable;
    @FXML
    private TableColumn<EventCount,String> name;
    @FXML
    private TableColumn<EventCount, Long> num;

    private ObservableList<EventCount> masterData = FXCollections.observableArrayList();

    public Counting(){
        masterData.add(new EventCount("AccessLog", CEPEngine.getCreatedInstance().getCountOfEvent(AccessLogEvent.class)));
        masterData.add(new EventCount("BlockPortScan", CEPEngine.getCreatedInstance().getCountOfEvent(BlockPortScanEvent.class)));
        masterData.add(new EventCount("BruteForceAttack", CEPEngine.getCreatedInstance().getCountOfEvent(BruteForceAttackEvent.class)));
        masterData.add(new EventCount("ConsecutiveFailedLogin", CEPEngine.getCreatedInstance().getCountOfEvent(ConsecutiveFailedLoginEvent.class)));
        masterData.add(new EventCount("FailedLogin", CEPEngine.getCreatedInstance().getCountOfEvent(FailedLoginEvent.class)));
        masterData.add(new EventCount("ClosedPortScan", CEPEngine.getCreatedInstance().getCountOfEvent(ClosedPortScanEvent.class)));
        masterData.add(new EventCount("PortCountSource", CEPEngine.getCreatedInstance().getCountOfEvent(PortCountSourceEvent.class)));
        masterData.add(new EventCount("PortScanEvent", CEPEngine.getCreatedInstance().getCountOfEvent(PortScanEvent.class)));
        masterData.add(new EventCount("TcpPacket", CEPEngine.getCreatedInstance().getCountOfEvent(TcpPacketEvent.class)));
        masterData.add(new EventCount("Forbidden", CEPEngine.getCreatedInstance().getCountOfEvent(ForbiddenEvent.class)));
    }

    @FXML
    private void initialize(){
        name.setCellValueFactory(new PropertyValueFactory<EventCount, String>("name"));
        num.setCellValueFactory(new PropertyValueFactory<EventCount, Long>("num"));

        FilteredList<EventCount> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<EventCount> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(counttable.comparatorProperty());
        counttable.setItems(masterData);
        this.update();
    }

    private void update(){
        Task<Void> myUpdatingTask = new Task<Void>(){
            @Override
            protected Void call() throws Exception{
                public void run(){
                    counttable.refresh();
                }
            }
        };
        Thread hilo = new Thread(myUpdatingTask);
        hilo.setDaemon(true);
        hilo.start();
    }


}
