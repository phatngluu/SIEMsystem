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

public class Controller {
    @FXML
    private TableView<Alert> alertview;
    @FXML
    private TableColumn<Alert, String> name;
    @FXML
    private TableColumn<Alert, java.util.Date> Date;
    @FXML
    private TableColumn<Alert, String> message;
    @FXML
    private TableColumn<Alert, String> priority;

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
        name.setCellValueFactory(new PropertyValueFactory<Alert, String>("name"));
        message.setCellValueFactory(new PropertyValueFactory<Alert, String>("message"));
        Date.setCellValueFactory(cellData -> (ObservableValue<java.util.Date>) cellData.getValue().getTimestamp());
        priority.setCellValueFactory(new PropertyValueFactory<Alert, String>("priority"));

        FilteredList<Alert> filteredData = new FilteredList<>(masterData, p -> true);
        SortedList<Alert> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(alertview.comparatorProperty());
        alertview.setItems(masterData);
    }

}
