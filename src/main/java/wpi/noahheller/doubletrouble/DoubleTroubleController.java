package wpi.noahheller.doubletrouble;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DoubleTroubleController {
    @FXML
    private Button greenButton1;
    @FXML
    private Button greenButton2;
    @FXML
    private Button greenButton3;
    @FXML
    private Button yellowButton1;
    @FXML
    private Button yellowButton2;
    @FXML
    private Button yellowButton3;
    @FXML
    private Button yellowButton4;
    @FXML
    private Button yellowButton5;
    @FXML
    private Button yellowButton6;
    @FXML
    private Button yellowButton7;
    @FXML
    private Button redButton1;
    @FXML
    private Button redButton2;
    @FXML
    private Button redButton3;
    @FXML
    private Button redButton4;
    @FXML
    private Button redButton5;
    @FXML
    private Button startButton;
    @FXML
    private Button endTurnButton;
    @FXML
    private BorderPane root;
    @FXML
    private VBox buttonRows;
    @FXML
    private HBox row2;
    @FXML
    private HBox row3;
    @FXML
    private HBox row4;
    @FXML
    private HBox row5;
    @FXML
    private ChoiceBox<GameStrategy> compGameStrategy;
    @FXML
    private Label message;
    @FXML
    private CheckBox playerGoesFirst;

    private final DoubleTroubleModel model;
    private final DoubleTroubleInteractor interactor;

    public DoubleTroubleController() {
        model = new DoubleTroubleModel();
        interactor = new DoubleTroubleInteractor(model);
    }

    @FXML
    public void initialize() {
        initUI();
    }

    private void initUI() {
        Button[] buttons = new Button[]{greenButton1, greenButton2, greenButton3, yellowButton1, yellowButton2, yellowButton3, yellowButton4, yellowButton5, yellowButton6, yellowButton7, redButton1, redButton2, redButton3, redButton4, redButton5};
        for (int i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].disableProperty().bind(model.colorButtonEnabled(i).not());
            buttons[i].setOnMouseClicked(event -> interactor.clickButton(finalI));
            buttons[i].prefWidthProperty().bind(root.widthProperty().divide(Constants.BUTTON_WIDTH_SCALING_FACTOR));
            buttons[i].prefHeightProperty().bind(root.widthProperty().divide(Constants.BUTTON_WIDTH_SCALING_FACTOR));
            buttons[i].styleProperty().bind(
                    Bindings.concat("-fx-border-width: ", root.widthProperty().divide(Constants.BUTTON_BORDER_WIDTH_SCALING_FACTOR))
            );
        }
        model.gameStrategyProperty().bind(compGameStrategy.selectionModelProperty());
        model.playerGoesFirst().bind(playerGoesFirst.selectedProperty());
        playerGoesFirst.disableProperty().bind(model.gameOptionsEnabledProperty().not());
        ObservableList<GameStrategy> strategies = FXCollections.observableArrayList(
                new RandomStrategy(),
                new OptimalStrategy()
        );
        compGameStrategy.setItems(strategies);
        compGameStrategy.getSelectionModel().select(0);
        compGameStrategy.setConverter(new ClassNameStringConverter<>());
        compGameStrategy.disableProperty().bind(model.gameOptionsEnabledProperty().not());
        message.textProperty().bind(model.messageProperty());
        compGameStrategy.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", root.widthProperty().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        playerGoesFirst.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", root.widthProperty().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        endTurnButton.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", root.widthProperty().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        startButton.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", root.widthProperty().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        message.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", root.widthProperty().divide(Constants.FONT_WIDTH_SCALING_FACTOR))
        );
        buttonRows.spacingProperty().bind(root.widthProperty().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row2.spacingProperty().bind(root.widthProperty().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row3.spacingProperty().bind(root.widthProperty().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row4.spacingProperty().bind(root.widthProperty().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        row5.spacingProperty().bind(root.widthProperty().divide(Constants.SPACING_WIDTH_SCALING_FACTOR));
        startButton.setOnMouseClicked(event -> interactor.resetGame());
        endTurnButton.setOnMouseClicked(event -> {
            //lock button states cant be updated via GUI
            boolean success = interactor.endTurn();
            if (!success) {
                return;
            }
            //grab buttons states still in UI thread
            Boolean[] buttonStates = model.buttonStates();
            GameStrategy strategy = model.getGameStrategy();
            interactor.runComputerTurn(buttonStates, strategy);
        });
    }
    public void shutdown(){
        interactor.shutdown();
    }
}
