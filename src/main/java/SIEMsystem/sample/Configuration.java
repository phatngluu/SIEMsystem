package SIEMsystem.sample;

import SIEMsystem.cep.CEPEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Configuration {
    @FXML
    private ChoiceBox<String> keyChoice = new ChoiceBox<>();
    @FXML
    private TextField valueTextField = new TextField();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private Button cancelButton = new Button();


    public Configuration() {
    }
    @FXML
    private void initialize(){
        //choicebox
        keyChoice.getItems().addAll("PORTSCAN_INET_ADDRESS", "PORTSCAN_EXCLUDE_PORTS",
                "PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS",
                "PORTSCAN_V_THROW_ALERT_EACH_SECONDS", "PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS",
                "PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_H_THROW_ALERT_EACH_SECONDS",
                "PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS", "WEBSERVER_LOG_FILE_PATH",
                "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD",
                "WEBSERVER_BRUTEFORCE_LOWER_THRESHOLD");
        keyChoice.setValue("PORTSCAN_INET_ADDRESS");

        //configuration scene
        CEPEngine cepEngine = CEPEngine.getCreatedInstance();
        saveButton.setOnAction(e -> {
                cepEngine.setProperty(getKey(keyChoice), valueTextField.getText());
        });
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

    public String getKey(ChoiceBox<String> keyChoice) {
        return keyChoice.getValue();
    }
}