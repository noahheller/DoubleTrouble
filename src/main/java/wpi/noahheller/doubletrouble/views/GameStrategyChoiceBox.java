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
        ChoiceBox<GameStrategy> compGameStrategy = new ChoiceBox<>();
        model.gameStrategyProperty().bind(compGameStrategy.selectionModelProperty());
        strategies = FXCollections.observableArrayList(
                new RandomStrategy(),
                new OptimalStrategy()
        );
        compGameStrategy.setItems(strategies);
        compGameStrategy.getSelectionModel().select(0);
        compGameStrategy.setConverter(new ClassNameStringConverter<>());
        compGameStrategy.disableProperty().bind(model.gameOptionsEnabledProperty().not());

        compGameStrategy.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", model.scalingWidth().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
    }
}
