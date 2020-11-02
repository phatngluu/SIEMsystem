package SIEMsystem.dashboard;

import SIEMsystem.alert.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField textField = new TextField();
    AlertManager alertManager = AlertManager.getInstance();

    public ChangePriority() throws ClassNotFoundException {
    }

    @FXML
    private void initialize() throws ClassNotFoundException {
        // choicebox
        keyChoice.getItems().addAll("BlockPortScanAlert", "BruteForceAttackAlert",
                "ConsecutiveFailedLoginAlert", "ClosedPortConnectionFailureAlert",
                "FailedLoginAlert", "ForbiddenAlert", "HorizontalPortScanAlert", "HighCPUUsageAlert",
                "HighMemUsageAlert", "VerticalPortScanAlert");
        keyChoice.setValue("BlockPortScanAlert");

        valueChoice.getItems().addAll("Low", "Medium", "High");
        valueChoice.setValue(alertManager.getProperty(getKey(keyChoice)));
        keyChoice.setOnAction(e -> {
            try {
                valueChoice.setValue(alertManager.getProperty(getKey(keyChoice)));
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        // change priority scene

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

    public String getValue(ChoiceBox<String> valueChoice) {
        return valueChoice.getValue();
    }
}