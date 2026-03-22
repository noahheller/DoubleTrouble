package wpi.noahheller.doubletrouble.views;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import wpi.noahheller.doubletrouble.Constants;
import wpi.noahheller.doubletrouble.DoubleTroubleModel;

public class DTGameConfigPane extends HBox {
    public DTGameConfigPane(DoubleTroubleModel model, Runnable onEndTurn, Runnable onStartGame) {
        this.setAlignment(Pos.CENTER);
        //check box
        CheckBox playerGoesFirst = new CheckBox("Go First");
        playerGoesFirst.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        model.playerGoesFirst().bind(playerGoesFirst.selectedProperty());
        playerGoesFirst.disableProperty().bind(model.gameOptionsEnabledProperty().not());

        //end turn button
        Button endTurnButton = new Button("End Turn");
        endTurnButton.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        endTurnButton.setOnMouseClicked(event -> {
            onEndTurn.run();
        });
        //start game button
        Button startGameButton = new Button("Start Game");
        startGameButton.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        startGameButton.setOnMouseClicked(event -> {
            onStartGame.run();
        });

        // game strategy choicebox
        GameStrategyChoiceBox choiceBox = new GameStrategyChoiceBox(model);
        this.spacingProperty().bind(model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR));
        this.getChildren().addAll(playerGoesFirst, endTurnButton, startGameButton, choiceBox);
    }
}
