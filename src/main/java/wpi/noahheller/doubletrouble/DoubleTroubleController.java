package wpi.noahheller.doubletrouble;

import javafx.scene.layout.Region;
import wpi.noahheller.doubletrouble.logic.GameStrategy;
import wpi.noahheller.doubletrouble.views.DTBorderPane;

public class DoubleTroubleController {
    private final DoubleTroubleModel model;
    private final DoubleTroubleInteractor interactor;
    private final DTBorderPane root;

    public DoubleTroubleController() {
        model = new DoubleTroubleModel();
        interactor = new DoubleTroubleInteractor(model);
        root = new DTBorderPane(model, this::onButtonClick, this::onEndTurn, this::onStartGame);
    }

    public void onButtonClick(int buttonIndex) {
        interactor.clickButton(buttonIndex);
    }

    public void onStartGame() {
        interactor.resetGame();
    }

    public void onEndTurn() {
        //lock button states cant be updated via GUI
        boolean success = interactor.endTurn();
        if (!success) {
            return;
        }
        //grab buttons states still in UI thread
        Boolean[] buttonStates = model.buttonStates();
        GameStrategy strategy = model.getGameStrategy();
        interactor.runComputerTurn(buttonStates, strategy);
    }

    public Region getView() {
        return root;
    }

    public void shutdown() {
        interactor.shutdown();
    }
}
