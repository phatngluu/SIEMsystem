package SIEMsystem.dashboard;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RestartNotification {
    @FXML
    private Button closeButton = new Button();
    public RestartNotification() {}
    @FXML
    private void initialize() {
        closeButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
