package SIEMsystem.dashboard;

import SIEMsystem.alert.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * This class is about creating the ChangePriority scene to change the alert priority
 * and store to priorities.properties.
 * @author Nguyen T. Nguyen
 */
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

    /**
     * this method is about creating an empty constructor.
     */
    public ChangePriority() throws ClassNotFoundException {
    }

    /**
     * this method is about creating functionality for ChoiceBox and Button.
     * keyChoice is the key field in priorities.properties. Eg.: HorizontalPortScanAlert
     * keyChoice is read as String and pass to alertManager.getProperty() and alertManager.setProperty() as Class type.
     * valueChoice is the value field in priorities.properties. Eg.: Low
     * valueChoice is read and pass to alertManager.gerProperty() as String type.
     * saveButton is about executing method alertManager.setPriority() with keyChoice and valueChoice value.
     * cancelButton is about closing the Change priority window on click.
     */
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

    /**
     * this method is about getting keyChoice value as String and return as Class type.
     * @param keyChoice is the selected value from ChoiceBox.
     * @return keyChoice value as Class type.
     */
    public Class getKey(ChoiceBox<String> keyChoice) throws ClassNotFoundException {
        return Class.forName("SIEMsystem.alert." + keyChoice.getValue());
    }

    /**
     * this method is about getting valueChoice value as String and return as String type.
     * @param valueChoice is the selected value from ChoiceBox.
     * @return keyChoice value as String type.
     */
    public String getValue(ChoiceBox<String> valueChoice) {
        return valueChoice.getValue();
    }
}