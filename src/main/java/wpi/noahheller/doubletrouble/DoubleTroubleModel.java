package wpi.noahheller.doubletrouble;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SingleSelectionModel;

import java.util.Arrays;

public class DoubleTroubleModel {
    private final BooleanProperty[] colorButtonEnabled;
    private final BooleanProperty playersTurn = new SimpleBooleanProperty(true);
    private final ObjectProperty<ButtonColor> selectedColorProperty = new SimpleObjectProperty<>(null);
    private final ObjectProperty<SingleSelectionModel<GameStrategy>> gameStrategyProperty =new SimpleObjectProperty<>(null);
    private final BooleanProperty strategySelectionEnabledProperty = new SimpleBooleanProperty(true);
    private final StringProperty messageProperty = new SimpleStringProperty(Constants.WELCOME_MESSAGE);

    public DoubleTroubleModel() {
        colorButtonEnabled = new SimpleBooleanProperty[Constants.BUTTON_COUNT];
        for (int i = 0; i < Constants.BUTTON_COUNT; i++) {
            colorButtonEnabled[i] = new SimpleBooleanProperty(true);
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
    BooleanProperty strategySelectionEnabledProperty() {
        return strategySelectionEnabledProperty;
    }

    boolean isStrategySelectionEnabled() {
        return strategySelectionEnabledProperty.get();
    }

    void setStrategySelectionEnabled(boolean enabled) {
        strategySelectionEnabledProperty.set(enabled);
    }

    public StringProperty messageProperty() {
        return messageProperty;
    }
    public String getMessage() {
        return messageProperty.get();
    }
    public void setMessage(String message){
        messageProperty.set(message);
    }
}
