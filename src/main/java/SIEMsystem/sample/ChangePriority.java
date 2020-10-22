package SIEMsystem.sample;

import SIEMsystem.alert.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;


public class ChangePriority {
    @FXML
    private ChoiceBox<String> keyChoice = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> valueChoice = new ChoiceBox<>();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private Button cancelButton = new Button();

    Class BlockPortScanAlert = Class.forName("SIEMsystem.alert.BlockPortScanAlert");
    Class BruteForceAttackAlert = Class.forName("SIEMsystem.alert.BruteForceAttackAlert");
    Class ConsecutiveFailedLoginAlert = Class.forName("SIEMsystem.alert.ConsecutiveFailedLoginAlert");
    Class FailedLoginAlert = Class.forName("SIEMsystem.alert.FailedLoginAlert");
    Class HorizontalPortScanAlert = Class.forName("SIEMsystem.alert.HorizontalPortScanAlert");
    Class UnauthorizedAlert = Class.forName("SIEMsystem.alert.UnauthorizedAlert");
    Class VerticalPortScanAlert = Class.forName("SIEMsystem.alert.VerticalPortScanAlert");

    public ChangePriority() throws ClassNotFoundException {
    }

    @FXML
    private void initialize(){
        //choicebox
        keyChoice.getItems().addAll("BlockPortScanAlert", "BruteForceAttackAlert", "ConsecutiveFailedLoginAlert", "FailedLoginAlert", "HorizontalPortScanAlert", "UnauthorizedAlert", "VerticalPortScanAlert");
        keyChoice.setValue("BlockPortScanAlert");

        valueChoice.getItems().addAll("Low", "Medium", "High");
        valueChoice.setValue("Low");

        //change priority scene
        AlertManager alertManager = AlertManager.getInstance();
        saveButton.setOnAction(e -> {
            try {
                alertManager.setPriority(getKey(keyChoice), getValue(valueChoice));
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
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