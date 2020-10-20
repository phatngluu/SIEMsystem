package SIEMsystem.dashboard;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Dashboard extends Application {
    Stage window;
    Scene scene1;

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        primaryStage.setTitle("SIEM system");

        Label label1 = new Label("SIEM System");

        //showAlertButton
        Button showAlertButton = new Button("Show alert");
        showAlertButton.setOnAction(e -> {
            ShowAlertScene showAlertScene = new ShowAlertScene();
            showAlertScene.start(primaryStage);
        });

        //layout 1
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(200,100);
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(label1, 1, 0);
        gridPane.add(showAlertButton, 1, 1);

        //scene 1
        scene1 = new Scene(gridPane, 300, 250);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}