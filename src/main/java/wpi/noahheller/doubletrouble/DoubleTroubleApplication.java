package wpi.noahheller.doubletrouble;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class DoubleTroubleApplication extends Application {
    DoubleTroubleController controller;
    @Override
    public void start(Stage stage) {
        controller = new DoubleTroubleController();
        Scene scene = new Scene(controller.getView(),Constants.STARTING_WIDTH,Constants.STARTING_HEIGHT);
        stage.setTitle(Constants.WINDOW_NAME);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
        controller.shutdown();
    }
}
