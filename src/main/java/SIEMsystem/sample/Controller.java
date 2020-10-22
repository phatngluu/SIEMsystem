package SIEMsystem.sample;

import SIEMsystem.alert.Alert;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileNotFoundException;

import com.espertech.esper.common.client.configuration.Configuration;

import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.WebserverModule;
import SIEMsystem.collector.EventCollector;
import SIEMsystem.collector.WebserverCollector;
import SIEMsystem.collector.PortscanCollector;
import SIEMsystem.event.ConsecutiveFailedLoginEvent;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.BlockPortScanEvent;
import SIEMsystem.event.BruteForceAttackEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.PortCountSourceEvent;
import SIEMsystem.event.SourceCountPortEvent;
import SIEMsystem.event.TcpPacketIncomingEvent;
import SIEMsystem.event.UnauthorizedEvent;

import java.util.Date;

public class Controller {
    @FXML
    private TableView<Alert> alertview;
    @FXML
    private TableColumn<Alert, String> namecol;
    @FXML
    private TableColumn<Alert, java.util.Date> Datecol;
    @FXML
    private TableColumn<Alert, String> messagecol;
    @FXML
    private TableColumn<Alert, String> prioritycol;

    private static ObservableList<Alert> masterData = FXCollections.observableArrayList();

    public Controller(){
    }

    @FXML
    private void initialize(){
        namecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("name"));
        messagecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("message"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Alert, java.util.Date>("timestamp"));
        prioritycol.setCellValueFactory(new PropertyValueFactory<Alert, String>("priority"));

        // Setting up and run the engine
        Configuration configuration = new Configuration();
        configuration.getCommon().addEventType(AccessLogEvent.class);
        configuration.getCommon().addEventType(FailedLoginEvent.class);
        configuration.getCommon().addEventType(UnauthorizedEvent.class);
        configuration.getCommon().addEventType(ConsecutiveFailedLoginEvent.class);
        configuration.getCommon().addEventType(BruteForceAttackEvent.class);
        configuration.getCommon().addEventType(TcpPacketIncomingEvent.class);
        configuration.getCommon().addEventType(SourceCountPortEvent.class);
        configuration.getCommon().addEventType(PortCountSourceEvent.class);
        configuration.getCommon().addEventType(BlockPortScanEvent.class);

        CEPEngine engine = CEPEngine.getNewInstance(configuration);
        // engine.activate(WebserverModule.getInstance());
        engine.activate(PortscanModule.getInstance());

        PortscanCollector portscanCollector = new PortscanCollector();
        // WebserverCollector webserverCollector = new WebserverCollector();
        portscanCollector.start();
        // webserverCollector.start();

        alertview.setItems(masterData);
    }

    public static void acceptAlert(Alert alert){
        masterData.add(alert);
    }

}
