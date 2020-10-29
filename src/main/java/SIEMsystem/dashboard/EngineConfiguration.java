package SIEMsystem.dashboard;

import SIEMsystem.cep.CEPEngine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.Pcaps;
import java.io.IOException;

public class EngineConfiguration {
    @FXML
    private ChoiceBox<String> keyChoice = new ChoiceBox<>();
    @FXML
    private TextField valueTextField = new TextField();
    @FXML
    private Button saveButton = new Button();
    @FXML
    private Button cancelButton = new Button();
    @FXML
    private Button okButton = new Button();
    @FXML
    private Label notifyLabel = new Label();
    Stage primaryStage;

    public EngineConfiguration() {
    }

    @FXML
    private void initialize() {
        // choicebox
        keyChoice.getItems().addAll("PORTSCAN_NETWORK_INTERFACE_NAME", "PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS",
                "PORTSCAN_V_THROW_ALERT_EACH_SECONDS", "PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS",
                "PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_H_THROW_ALERT_EACH_SECONDS", "PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS",
                "PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_TIME_WINDOW_IN_SECONDS",
                "PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_ATTEMPT_THRESHOLD", "PORTSCAN_EXCLUDE_PORTS",
                "WEBSERVER_BRUTEFORCE_LOWER_THRESHOLD", "WEBSERVER_BRUTE_FORCE_TIME_WINDOW_IN_SECONDS",
                "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD", "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_TIME_WINDOW_IN_SECONDS",
                "WEBSERVER_LOG_FILE_PATH", "RESOURCE_CPU_USAGE_THRESHOLD",
                "RESOURCE_MEM_USAGE_THRESHOLD", "RESOURCE_TIME_OF_WINDOW_IN_SECONDS");
        keyChoice.setValue("PORTSCAN_NETWORK_INTERFACE_NAME");
        valueTextField.setText(CEPEngine.getCreatedInstance().getProperty(getKey(keyChoice)));
        keyChoice.setOnAction(e -> {
            valueTextField.setText(CEPEngine.getCreatedInstance().getProperty(getKey(keyChoice)));
        });

        // configuration scene
        saveButton.setOnAction(e -> {
            boolean validConfig = true;
            switch (getKey(keyChoice)) {
                case "PORTSCAN_NETWORK_INTERFACE_NAME":
                    try {
                        if (Pcaps.getDevByName(valueTextField.getText()) == null) {
                            notifyLabel.setText("Interface " + valueTextField.getText() + " is unavailable.");
                            notifyLabel.setTextFill(Color.web("#FF0000"));
                            validConfig = false;
                        } else {
                            notifyLabel.setText("Interface " + valueTextField.getText() + " is chosen.");
                            notifyLabel.setTextFill(Color.web("#2ECF20"));
                        }
                    } catch (PcapNativeException e1) {
                        notifyLabel.setText("Interface " + valueTextField.getText() + " is unavailable.");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
                    }
                    break;
                case "PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS":
                case "PORTSCAN_V_THROW_ALERT_EACH_SECONDS":
                case "PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS":
                case "PORTSCAN_H_THROW_ALERT_EACH_SECONDS":
                case "PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS":
                case "WEBSERVER_BRUTE_FORCE_TIME_WINDOW_IN_SECONDS":
                case "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_TIME_WINDOW_IN_SECONDS":
                case "PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_TIME_WINDOW_IN_SECONDS":
                case "RESOURCE_CPU_USAGE_THRESHOLD":
                case "RESOURCE_MEM_USAGE_THRESHOLD":
                case "RESOURCE_TIME_OF_WINDOW_IN_SECONDS": {
                    try {
                        if (Double.parseDouble(valueTextField.getText()) <= 0) {
                            notifyLabel.setText("Please enter positive number");
                            notifyLabel.setTextFill(Color.web("#FF0000"));
                            validConfig = false;
                        } else {
                            notifyLabel.setText("Value " + valueTextField.getText() + " is set");
                            notifyLabel.setTextFill(Color.web("2ECF20"));
                        }
                    } catch (Exception ex) {
                        notifyLabel.setText("Please enter positive number");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
                    }
                } break;

                case "PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS":
                case "WEBSERVER_BRUTEFORCE_LOWER_THRESHOLD":
                case "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD":
                case "PORTSCAN_CLOSED_PORT_CONNECTION_FAILURE_ATTEMPT_THRESHOLD":
                case "PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS": {
                    try {
                        if (Integer.parseInt(valueTextField.getText()) <= 0) {
                            notifyLabel.setText("Please enter positive number");
                            notifyLabel.setTextFill(Color.web("#FF0000"));
                            validConfig = false;
                        } else {
                            notifyLabel.setText("Value " + valueTextField.getText() + " is set");
                            notifyLabel.setTextFill(Color.web("2ECF20"));
                        }
                    } catch (Exception ex) {
                        notifyLabel.setText("Please enter positive number");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
                    }
                } break;

                case "PORTSCAN_EXCLUDE_PORTS":
                    try {
                        if (valueTextField.getText().matches(".*[A-Za-z-+_!@#$%^&*, ?;:\"']")) {
                            notifyLabel.setText("Please enter port number from 0 to 65353");
                            notifyLabel.setTextFill(Color.web("#FF0000"));
                            validConfig = false;
                        } else {
                            notifyLabel.setText("Value " + valueTextField.getText() + " is set. \nMake sure your ports are from 0 to 65353");
                            notifyLabel.setTextFill(Color.web("2ECF20"));
                        }
                    } catch (Exception ex) {
                        notifyLabel.setText("Please enter port number from 0 to 65353");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
                    }
                    break;

                case "WEBSERVER_LOG_FILE_PATH":
                    if (valueTextField.getText().matches("^+(?=.*[-+_!@#$%^&*, ?;:\"']).+$")) {
                        notifyLabel.setText("File path can not contain special character");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
                    } else {
                        notifyLabel.setText("Webserver log file path is set. \nMake sure your path is correct!");
                        notifyLabel.setTextFill(Color.web("2ECF20"));
                    }
                    break;
            }

            if (validConfig) {
                CEPEngine.getCreatedInstance().setProperty(getKey(keyChoice), valueTextField.getText());
            }
        });
        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        okButton.setOnAction(e -> {
            Stage stage = (Stage) okButton.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/restartNotification.fxml"));
            Parent root;
            try {
                root = loader.load();
                Stage newStage = new Stage();
                // set what you want on your stage
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setTitle("Change Priority");
                newStage.setScene(new Scene(root));
                newStage.setResizable(false);
                newStage.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    public String getKey(ChoiceBox<String> keyChoice) {
        return keyChoice.getValue();
    }
}