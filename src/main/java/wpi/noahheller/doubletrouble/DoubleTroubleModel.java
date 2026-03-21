package wpi.noahheller.doubletrouble;

import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.scene.control.SingleSelectionModel;

import java.util.Arrays;

public class DoubleTroubleModel {
    private final BooleanProperty[] colorButtonEnabled;
    private final BooleanProperty playersTurn = new SimpleBooleanProperty(true);
    private final ObjectProperty<ButtonColor> selectedColorProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<SingleSelectionModel<GameStrategy>> gameStrategyProperty = new SimpleObjectProperty<>(null);
    private final BooleanProperty gameOptionsEnabledProperty = new SimpleBooleanProperty(true);
    private final StringProperty messageProperty = new SimpleStringProperty(Constants.WELCOME_MESSAGE);
    private final BooleanProperty playerGoesFirst = new SimpleBooleanProperty(false);

    public DoubleTroubleModel() {
        colorButtonEnabled = new SimpleBooleanProperty[Constants.BUTTON_COUNT];
        for (int i = 0; i < Constants.BUTTON_COUNT; i++) {
            colorButtonEnabled[i] = new SimpleBooleanProperty(false);
        }
    }


    BooleanProperty colorButtonEnabled(int button) {
        return colorButtonEnabled[button];
    }

    boolean isColorButtonEnabled(int button) {
        return colorButtonEnabled(button).get();
    }

    void setColorButtonEnabled(int button, boolean enabled) {
        colorButtonEnabled(button).set(enabled);
    }

    BooleanProperty playersTurn() {
        return playersTurn;
    }

    boolean isPlayersTurn() {
        return playersTurn.get();
    }

    void setPlayersTurn(boolean isPlayersTurn) {
        playersTurn.set(isPlayersTurn);
    }

    ObjectProperty<ButtonColor> selectedColorProperty() {
        return selectedColorProperty;
    }

    ButtonColor getSelectedColor() {
        return selectedColorProperty.get();
    }

    void setSelectedColor(ButtonColor color) {
        selectedColorProperty.set(color);
    }

    ObjectProperty<SingleSelectionModel<GameStrategy>> gameStrategyProperty() {
        return gameStrategyProperty;
    }

    GameStrategy getGameStrategy() {
        return gameStrategyProperty.get().getSelectedItem();
    }

    Boolean[] buttonStates() {
        return Arrays.stream(colorButtonEnabled).map(ObservableBooleanValue::get).toArray(Boolean[]::new);
    }

    BooleanProperty gameOptionsEnabledProperty() {
        return gameOptionsEnabledProperty;
    }

    boolean aresGameOptionsEnabled() {
        return gameOptionsEnabledProperty.get();
    }

    void setGameOptionsEnabled(boolean enabled) {
        gameOptionsEnabledProperty.set(enabled);
    }

    BooleanProperty playerGoesFirst() {
        return playerGoesFirst;
    }

    boolean doesPlayerGoFirst() {
        return playerGoesFirst.get();
    }

    void setPlayerGoesFirst(boolean goesFirst) {
        playerGoesFirst.set(goesFirst);
    }

    StringProperty messageProperty() {
        return messageProperty;
    }

    String getMessage() {
        return messageProperty.get();
    }

    void setMessage(String message) {
        messageProperty.set(message);
    }

}
