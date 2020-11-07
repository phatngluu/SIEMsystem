package SIEMsystem.dashboard;

import SIEMsystem.alert.Alert;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
/**
 * This class is about creating the dashboard scene with table view and functional button.
 */
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Counting.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Event Statistics");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent e){
                Counting.setStop();
            }
        });        
        stage.show();
    }

    /**
     * this method attachs the Change Priority button with functions to open Change priority scene
     * after clicking the button.
     * @author Nguyen T. Nguyen
     */
    @FXML
    public void handleButtonClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/changePriority.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change Priority");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * this method attachs the Change configuration button with functions to open Change configuration scene
     * after clicking the button.
     * @author Nguyen T. Nguyen
     */
    @FXML
    public void handleConfigurationClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/engineConfiguration.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Change Configuration");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public static void acceptAlert(Alert alert){
        masterData.add(alert);
    }
}
