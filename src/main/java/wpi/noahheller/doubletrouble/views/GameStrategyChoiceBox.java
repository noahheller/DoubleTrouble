package wpi.noahheller.doubletrouble.views;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import wpi.noahheller.doubletrouble.*;
import wpi.noahheller.doubletrouble.logic.GameStrategy;
import wpi.noahheller.doubletrouble.logic.OptimalStrategy;
import wpi.noahheller.doubletrouble.logic.RandomStrategy;

public class GameStrategyChoiceBox extends ChoiceBox<GameStrategy> {
    private final ObservableList<GameStrategy> strategies;

    public GameStrategyChoiceBox(DoubleTroubleModel model) {
        model.gameStrategyProperty().bind(this.selectionModelProperty());
        strategies = FXCollections.observableArrayList(
                new RandomStrategy(),
                new OptimalStrategy()
        );
        this.setItems(strategies);
        this.getSelectionModel().select(0);
        this.setConverter(new ClassNameStringConverter<>());
        this.disableProperty().bind(model.gameOptionsEnabledProperty().not());

        this.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
    }
}
