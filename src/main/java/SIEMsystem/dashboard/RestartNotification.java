package SIEMsystem.dashboard;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
/**
 * This class is about creating the RestartNotification scene after clicking okButton in
 * Change configuration scene.
 * @author Nguyen T. Nguyen
 */
public class RestartNotification {
    @FXML
    private Button closeButton = new Button();

    /**
     * this method is about creating an empty constructor.
     */
    public RestartNotification() {}

    /**
     * this method is about closing the Application after clicking the closeButton.
     * the closeButton is about to exit the JavaFX Platform and exit JVM on click.
     */
    @FXML
    private void initialize() {
        closeButton.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });
    }
}
