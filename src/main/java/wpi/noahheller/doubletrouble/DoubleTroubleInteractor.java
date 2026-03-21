package wpi.noahheller.doubletrouble;

import javafx.concurrent.Task;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DoubleTroubleInteractor {
    private final DoubleTroubleModel model;

    public DoubleTroubleInteractor(DoubleTroubleModel model) {
        this.model = model;
    }

    public void clickButton(int buttonIndex) {
        ButtonColor buttonColor = ButtonColor.buttonIndexToColor(buttonIndex);
        if (model.isPlayersTurn()) {
            if (model.getSelectedColor() == null) {
                model.setSelectedColor(buttonColor);
            }
            if (model.getSelectedColor().equals(buttonColor)) {
                model.setColorButtonEnabled(buttonIndex, false);
            }
        }
    }

    public void resetButtons() {
        if (!model.isPlayersTurn()) {
            return;
        }
        for (int i = 0; i < Constants.BUTTON_COUNT; i++) {
            model.setColorButtonEnabled(i, true);
        }
        model.setPlayersTurn(true);
        model.setMessage(Constants.WELCOME_MESSAGE);
        model.setSelectedColor(null);
        model.setStrategySelectionEnabled(true);
    }

    public boolean endTurn() {
        if (model.isPlayersTurn() && model.getSelectedColor() == null) {
            return false;
        }
        model.setPlayersTurn(false);
        model.setStrategySelectionEnabled(false);
        model.setSelectedColor(null);
        return true;
    }

    private void updateBoardFromComputer(List<Integer> buttonIndexes) {
        if (buttonIndexes.isEmpty()){
            model.messageProperty().set(Constants.PLAYER_WIN_MESSAGE);
            return;
        }
        for (int i : buttonIndexes) {
            model.setColorButtonEnabled(i, false);
        }
        //check if game over

        Boolean[] buttonStates = model.buttonStates();
        boolean gameOver = true;
        for (Boolean buttonState : buttonStates) {
            if (buttonState) {
                gameOver = false;
                break;
            }
        }
        if(gameOver){
            model.messageProperty().set(Constants.COMPUTER_WIN_MESSAGE);
        }
    }

    private void endComputerTurn() {
        model.setStrategySelectionEnabled(false);
        model.setPlayersTurn(true);
    }

    private List<Integer> chooseComputerMoves(Boolean[] buttonStates, GameStrategy strategy) {
        return strategy.pickTiles(buttonStates);
    }
    public Task<List<Integer>> getComputerTask(Boolean[] buttonStates, GameStrategy gameStrategy) {
        Task<List<Integer>> computerMoves = new Task<>() {
            @Override
            protected List<Integer> call() {
                return chooseComputerMoves(buttonStates,gameStrategy);
            }
        };
        computerMoves.setOnSucceeded(event1 -> {
            updateBoardFromComputer(computerMoves.getValue());
            endComputerTurn();
        });
        return computerMoves;
    }
}
