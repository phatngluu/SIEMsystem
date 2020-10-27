package SIEMsystem.sample;

import SIEMsystem.cep.CEPEngine;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.espertech.esper.common.client.configuration.Configuration;

import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;

import SIEMsystem.event.ConsecutiveFailedLoginEvent;
import SIEMsystem.event.AccessLogEvent;
import SIEMsystem.event.BlockPortScanEvent;
import SIEMsystem.event.BruteForceAttackEvent;
import SIEMsystem.event.ClosedPortScanEvent;
import SIEMsystem.event.FailedLoginEvent;
import SIEMsystem.event.OpenPortScanEvent;
import SIEMsystem.event.PortCountSourceEvent;
import SIEMsystem.event.SourceCountPortEvent;
import SIEMsystem.event.TcpPacketEvent;
import SIEMsystem.event.PortScanEvent;
import SIEMsystem.event.ForbiddenEvent;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.WebserverModule;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
    private Label notifyLabel = new Label();

    public EngineConfiguration() {
    }

    @FXML
    private void initialize() {
        // choicebox
        keyChoice.getItems().addAll("PORTSCAN_NETWORK_INTERFACE_NAME", "PORTSCAN_EXCLUDE_PORTS",
                "PORTSCAN_V_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_V_MINIMUM_NUMBER_OF_PORTS",
                "PORTSCAN_V_THROW_ALERT_EACH_SECONDS", "PORTSCAN_H_MINIMUM_NUMBER_OF_HOSTS",
                "PORTSCAN_H_TIME_OF_WINDOW_IN_SECONDS", "PORTSCAN_H_THROW_ALERT_EACH_SECONDS",
                "PORTSCAN_B_TIME_OF_WINDOW_IN_SECONDS", "WEBSERVER_LOG_FILE_PATH",
                "WEBSERVER_CONSECUTIVE_FAILEDLOGIN_LOWER_THRESHOLD", "WEBSERVER_BRUTEFORCE_LOWER_THRESHOLD");
        keyChoice.setValue("PORTSCAN_NETWORK_INTERFACE_NAME");

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
                            notifyLabel.setTextFill(Color.web("#15FF00"));
                        }
                    } catch (PcapNativeException e1) {
                        notifyLabel.setText("Interface " + valueTextField.getText() + " is unavailable.");
                        notifyLabel.setTextFill(Color.web("#FF0000"));
                        validConfig = false;
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
    }

    public String getKey(ChoiceBox<String> keyChoice) {
        return keyChoice.getValue();
    }
}