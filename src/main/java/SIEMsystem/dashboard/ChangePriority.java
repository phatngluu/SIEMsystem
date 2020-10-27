package SIEMsystem.dashboard;

import SIEMsystem.alert.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

@SuppressWarnings("rawtypes")
public class ChangePriority {
    @FXML
    private ChoiceBox<String> keyChoice = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> valueChoice = new ChoiceBox<>();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private Button cancelButton = new Button();

    public ChangePriority() throws ClassNotFoundException {
    }

    @FXML
    private void initialize(){
        //choicebox
        keyChoice.getItems().addAll("BlockPortScanAlert", "BruteForceAttackAlert", "ConsecutiveFailedLoginAlert", "FailedLoginAlert", "HorizontalPortScanAlert", "ForbiddenAlert", "VerticalPortScanAlert");
        keyChoice.setValue("BlockPortScanAlert");

        valueChoice.getItems().addAll("Low", "Medium", "High");
        valueChoice.setValue("Low");

        //change priority scene
        AlertManager alertManager = AlertManager.getInstance();
        saveButton.setOnAction(e -> {
            try {
                alertManager.setPriority(getKey(keyChoice), getValue(valueChoice));
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException: \"" + keyChoice.getValue() + "\"");
            }
        });
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

    public Class getKey(ChoiceBox<String> keyChoice) throws ClassNotFoundException {
        return Class.forName("SIEMsystem.alert." + keyChoice.getValue());
    }

    public String getValue(ChoiceBox<String> valueChoice){
        return valueChoice.getValue();
    }
}