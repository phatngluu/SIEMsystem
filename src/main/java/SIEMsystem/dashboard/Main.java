package SIEMsystem.dashboard;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.espertech.esper.common.client.configuration.Configuration;
import SIEMsystem.cep.CEPEngine;
import SIEMsystem.cep.PortscanModule;
import SIEMsystem.cep.ResourceModule;
import SIEMsystem.cep.WebserverModule;
import SIEMsystem.collector.WebserverCollector;
import SIEMsystem.collector.PortscanCollector;
import SIEMsystem.collector.ResourceCollector;
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
import SIEMsystem.event.ResourceMonitorEvent;
import SIEMsystem.event.ForbiddenEvent;
import SIEMsystem.event.HighCPUUsageEvent;
import SIEMsystem.event.HighMemoryUsageEvent;
import javafx.stage.WindowEvent;

import java.io.File;
import java.net.URL;

public class Main extends Application {
    private static boolean engineStarted;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        engineStarted = false;

        /*URL url = new File("src/main/java/SIEMsystem/dashboard/preloader.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("SIEM System Dashboard - Team Uranus");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();*/

        CEPEngineInitializer initializer = new CEPEngineInitializer();
        initializer.start();

        // URL url = new File("src/main/java/SIEMsystem/dashboard/dashboard.fxml").toURI().toURL();
        // Parent root = FXMLLoader.load(url);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/dashboard.fxml"));
        Parent root = loader.load();
        // engineStarted = true;
        primaryStage.setTitle("SIEM System Dashboard - Team Uranus");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    private class CEPEngineInitializer extends Thread {

        @Override
        public void run() {
            // Setting up and run the engine
            Configuration configuration = new Configuration();
            configuration.getCommon().addEventType(AccessLogEvent.class);
            configuration.getCommon().addEventType(FailedLoginEvent.class);
            configuration.getCommon().addEventType(ForbiddenEvent.class);
            configuration.getCommon().addEventType(ConsecutiveFailedLoginEvent.class);
            configuration.getCommon().addEventType(BruteForceAttackEvent.class);
            configuration.getCommon().addEventType(SourceCountPortEvent.class);
            configuration.getCommon().addEventType(PortCountSourceEvent.class);
            configuration.getCommon().addEventType(BlockPortScanEvent.class);
            configuration.getCommon().addEventType(TcpPacketEvent.class);
            configuration.getCommon().addEventType(OpenPortScanEvent.class);
            configuration.getCommon().addEventType(ClosedPortScanEvent.class);
            configuration.getCommon().addEventType(PortScanEvent.class);
            configuration.getCommon().addEventType(ResourceMonitorEvent.class);
            configuration.getCommon().addEventType(HighCPUUsageEvent.class);
            configuration.getCommon().addEventType(HighMemoryUsageEvent.class);

            CEPEngine engine = CEPEngine.getNewInstance(configuration);
            engine.activate(WebserverModule.getInstance());
            engine.activate(PortscanModule.getInstance());
            engine.activate(ResourceModule.getInstance());
            engineStarted = true;

            WebserverCollector webserverCollector = new WebserverCollector();
            PortscanCollector portscanCollector = new PortscanCollector();
            ResourceCollector resourceCollector = new ResourceCollector();
            
            webserverCollector.start();
            portscanCollector.start();
            resourceCollector.start();
        }
        
    }
}
