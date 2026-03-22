package wpi.noahheller.doubletrouble.views;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.util.Builder;
import wpi.noahheller.doubletrouble.ButtonColor;
import wpi.noahheller.doubletrouble.Constants;
import wpi.noahheller.doubletrouble.DoubleTroubleApplication;
import wpi.noahheller.doubletrouble.DoubleTroubleModel;

import java.util.function.Consumer;

public class ButtonBuilder implements Builder<Button> {

    private final ButtonColor color;
    private final int index;
    private final Consumer<Integer> onClick;
    private final DoubleTroubleModel model;

    public ButtonBuilder(DoubleTroubleModel model, ButtonColor color, int index, Consumer<Integer> onClick) {
        this.model = model;
        this.color = color;
        this.index = index;
        this.onClick = onClick;
    }

    @Override
    public Button build() {
        Button button = new Button();
        button.disableProperty().bind(model.colorButtonEnabled(index).not());
        button.prefWidthProperty().bind(model.scalingWidth().divide(Constants.BUTTON_WIDTH_SCALING_FACTOR));
        button.prefHeightProperty().bind(model.scalingWidth().divide(Constants.BUTTON_WIDTH_SCALING_FACTOR));
        button.styleProperty().bind(
                Bindings.concat("-fx-border-width: ", model.scalingWidth().divide(Constants.BUTTON_BORDER_WIDTH_SCALING_FACTOR))
        );
        button.setOnMouseClicked(event -> onClick.accept(index));
        try{
            String styleSheet = this.getClass().getResource("dtButton.css").toExternalForm();
            button.getStylesheets().add(styleSheet);
            button.getStyleClass().add(color.getStyleClassName());
        }catch (NullPointerException e){
            System.err.println("Could not find button stylesheet");
        }
        return button;
    }
}
