package SIEMsystem.sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<Alert> masterData = FXCollections.observableArrayList();

    public Controller(){
        masterData.add(new Alert("a"));
        masterData.add(new Alert("b"));
        masterData.add(new Alert("c"));
        masterData.add(new Alert("d"));
        masterData.add(new Alert("e"));
    }

    @FXML
    private void initialize(){
        namecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("name"));
        messagecol.setCellValueFactory(new PropertyValueFactory<Alert, String>("message"));
        Datecol.setCellValueFactory(new PropertyValueFactory<Alert, java.util.Date>("timestamp"));
        prioritycol.setCellValueFactory(new PropertyValueFactory<Alert, String>("priority"));

        alertview.setItems(masterData);
    }
}
