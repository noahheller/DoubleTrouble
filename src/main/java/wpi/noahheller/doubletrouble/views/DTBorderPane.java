package wpi.noahheller.doubletrouble.views;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import wpi.noahheller.doubletrouble.Constants;
import wpi.noahheller.doubletrouble.DoubleTroubleModel;

import java.util.function.Consumer;

public class DTBorderPane extends BorderPane {
    public DTBorderPane(DoubleTroubleModel model, Consumer<Integer> onButtonClick, Runnable onEndTurn, Runnable onStartGame) {
        model.scalingWidth().bind(this.widthProperty());
        Label welcomeLabel = new Label();
        welcomeLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        welcomeLabel.textProperty().bind(model.messageProperty());
        this.setTop(welcomeLabel);
        this.setCenter(new DTGameBoard(model, onButtonClick));
        this.setBottom(new DTGameConfigPane(model, onEndTurn, onStartGame));
        setAlignment(welcomeLabel,Pos.CENTER);
    }
}
