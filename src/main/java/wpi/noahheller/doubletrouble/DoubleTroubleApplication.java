package wpi.noahheller.doubletrouble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DoubleTroubleApplication extends Application {
    DoubleTroubleController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DoubleTroubleApplication.class.getResource("double-trouble.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 320);
        controller = fxmlLoader.getController();
        stage.setTitle("Double Trouble");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        controller.shutdown();
    }
}
