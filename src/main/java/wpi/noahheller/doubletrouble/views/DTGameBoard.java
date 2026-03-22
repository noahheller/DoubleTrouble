package wpi.noahheller.doubletrouble.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import wpi.noahheller.doubletrouble.ButtonColor;
import wpi.noahheller.doubletrouble.Constants;
import wpi.noahheller.doubletrouble.DoubleTroubleModel;

import java.util.function.Consumer;

public class DTGameBoard extends VBox {

    public DTGameBoard(DoubleTroubleModel model, Consumer<Integer> onClick) {
        Button greenButton1 = new ButtonBuilder(model, ButtonColor.GREEN, 0, onClick).build(); //first row is just a button.
        HBox row2 = new HBox();
        HBox row3 = new HBox();
        HBox row4 = new HBox();
        HBox row5 = new HBox();

        row2.spacingProperty().bind(model.scalingWidth().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row3.spacingProperty().bind(model.scalingWidth().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row4.spacingProperty().bind(model.scalingWidth().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row5.spacingProperty().bind(model.scalingWidth().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        this.spacingProperty().bind(model.scalingWidth().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));

        row2.setAlignment(Pos.CENTER);
        row3.setAlignment(Pos.CENTER);
        row4.setAlignment(Pos.CENTER);
        row5.setAlignment(Pos.CENTER);
        //create second row buttons
        for (int i = 1; i < 3; i++) {
            ButtonBuilder buttonBuilder = new ButtonBuilder(model, ButtonColor.GREEN, i, onClick);
            row2.getChildren().add(buttonBuilder.build());
        }
        //create third row buttons
        for (int i = 3; i < 6; i++) {
            ButtonBuilder buttonBuilder = new ButtonBuilder(model, ButtonColor.YELLOW, i,onClick);
            row3.getChildren().add(buttonBuilder.build());
        }
        //create fourth row buttons
        for (int i = 6; i < 10; i++) {
            ButtonBuilder buttonBuilder = new ButtonBuilder(model, ButtonColor.YELLOW, i,onClick);
            row4.getChildren().add(buttonBuilder.build());
        }
        //create fifth row buttons
        for (int i = 10; i < 15; i++) {
            ButtonBuilder buttonBuilder = new ButtonBuilder(model, ButtonColor.RED, i,onClick);
            row5.getChildren().add(buttonBuilder.build());
        }
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(greenButton1, row2, row3, row4, row5);
    }
}
