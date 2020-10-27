package SIEMsystem.sample;

import SIEMsystem.alert.Alert;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileNotFoundException;

import com.espertech.esper.common.client.configuration.Configuration;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Dashboard {
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
    @FXML
    private Button changePriorityButton = new Button();
    @FXML
    private Button changeConfigurationButton = new Button();

    private static ObservableList<Alert> masterData = FXCollections.observableArrayList();

    public Dashboard(){
    }

    @FXML
    private void initialize(){
        namecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("name"));
        messagecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("message"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Alert, java.util.Date>("timestamp"));
        prioritycol.setCellValueFactory(new PropertyValueFactory<Alert, String>("priority"));

        alertview.setItems(masterData);
    }

    // open change priority window
    @FXML
    public void handleCountingButtonClick(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/java/SIEMsystem/sample/Counting.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change Priority");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void handleButtonClick(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/java/SIEMsystem/sample/changePriority.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change Priority");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void handleConfigurationClick(ActionEvent actionEvent) throws IOException {
        URL url = new File("src/main/java/SIEMsystem/sample/engineConfiguration.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change configuration");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void acceptAlert(Alert alert){
        masterData.add(alert);
    }
}
