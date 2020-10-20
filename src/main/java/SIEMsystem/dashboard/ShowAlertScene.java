package SIEMsystem.dashboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ShowAlertScene extends Application {
    Stage window;
    Scene scene2;

    TableView<Alert> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        primaryStage.setTitle("Show Alert");
        Label label2 = new Label("SHOW ALERT");
        label2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        //Alert Name column
        TableColumn<Alert, String> alertNameColumn = new TableColumn<>("Alert name");
        alertNameColumn.setMinWidth(200);
        alertNameColumn.setCellValueFactory(new PropertyValueFactory<>("alert name"));

        //Alert Message column
        TableColumn<Alert, String> alertMessageColumn = new TableColumn<>("Message");
        alertMessageColumn.setMinWidth(200);
        alertMessageColumn.setCellValueFactory(new PropertyValueFactory<>("message"));

        //Alert Timestamp column
        TableColumn<Alert, String> alertTimestampColumn = new TableColumn<>("Timestamp");
        alertTimestampColumn.setMinWidth(200);
        alertTimestampColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        //Alert priority column
        TableColumn<Alert, String> alertPriorityColumn = new TableColumn<>("Priority");
        alertPriorityColumn.setMinWidth(200);
        alertPriorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));

        table = new TableView<>();
        table.getColumns().addAll(alertNameColumn,alertMessageColumn,alertTimestampColumn,alertPriorityColumn);

        //backButton
        Button backButton = new Button("Back");
        backButton.setMinSize(50, 30);
        //backButton.setOnAction(e -> window.setScene(dashboard_1.scene1));

        //config button
        Button configButton = new Button("config");
        configButton.setMinSize(50, 30);
        //configButton.setOnAction();

        //sth button
        Button sthButton = new Button("sth");
        sthButton.setMinSize(50, 30);
        //configuration.setOnAction();

        //layout 2
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500,300);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(label2, 1 , 0);
        gridPane.add(table,1, 1);
        gridPane.add(backButton, 1, 2);
        //gridPane.add(configButton, 2, 2);

        //layout 3
        TilePane tilePane = new TilePane();
        tilePane.setPadding(new Insets(47, 10, 10, 10));
        tilePane.setVgap(5);
        tilePane.setOrientation(Orientation.VERTICAL);
        tilePane.setTileAlignment(Pos.CENTER_LEFT);
        tilePane.getChildren().addAll(configButton,sthButton);


        //scene 2
        scene2 = new Scene(new HBox(gridPane, tilePane), 850, 500);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
    //get alert
/*    public ObservableList<Alert> getAlert() {
        ObservableList<Alert> alerts = FXCollections.observableArrayList();
        alerts.add();
    }*/
}
