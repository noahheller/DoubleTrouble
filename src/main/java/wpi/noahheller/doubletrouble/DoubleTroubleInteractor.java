package wpi.noahheller.doubletrouble;

import javafx.concurrent.Task;

import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DoubleTroubleInteractor {
    private final DoubleTroubleModel model;
    private final ExecutorService computerExecutor = Executors.newSingleThreadExecutor();
    private final Random random = new Random();

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

    public void resetGame() {
        model.setMessage(Constants.WELCOME_MESSAGE);
        model.setSelectedColor(null);
        boolean playerStarts = random.nextBoolean();
        model.setStrategySelectionEnabled(playerStarts);
        model.setPlayersTurn(playerStarts);
        for (int i = 0; i < Constants.BUTTON_COUNT; i++) {
            model.setColorButtonEnabled(i, true);
        }
        if (!playerStarts) {
            Boolean[] buttonStates = model.buttonStates();
            GameStrategy strategy = model.getGameStrategy();
            Task<List<Integer>> computerMoves = getComputerTask(buttonStates, strategy);
            computerExecutor.submit(computerMoves);
        }
    }

    public boolean endTurn() {
        if (model.isPlayersTurn() && model.getSelectedColor() == null) {
            return false;
        }
        model.setPlayersTurn(false);
        model.setSelectedColor(null);
        return true;
    }

    private void updateBoardFromComputer(List<Integer> buttonIndexes) {
        if (buttonIndexes.isEmpty()) {
            model.messageProperty().set(Constants.PLAYER_WIN_MESSAGE);
            return;
        }
        for (int i : buttonIndexes) {
            model.setColorButtonEnabled(i, false);
        }
        //check if game over
        Boolean[] buttonStates = model.buttonStates();
        boolean gameOver = true;
        for (boolean buttonState : buttonStates) {
            if (buttonState) {
                gameOver = false;
                break;
            }
        }
        if (gameOver) {
            model.messageProperty().set(Constants.COMPUTER_WIN_MESSAGE);
        }
    }

    private void endComputerTurn() {
        model.setPlayersTurn(true);
    }

    private List<Integer> chooseComputerMoves(Boolean[] buttonStates, GameStrategy strategy) {
        return strategy.pickTiles(buttonStates);
    }

    private Task<List<Integer>> getComputerTask(Boolean[] buttonStates, GameStrategy gameStrategy) {
        Task<List<Integer>> computerMoves = new Task<>() {
            @Override
            protected List<Integer> call() {
                return chooseComputerMoves(buttonStates, gameStrategy);
            }
        };
        computerMoves.setOnSucceeded(event1 -> {
            updateBoardFromComputer(computerMoves.getValue());
            endComputerTurn();
        });
        return computerMoves;
    }

    public void runComputerTurn(Boolean[] buttonStates, GameStrategy strategy) {
        Task<List<Integer>> computerMoves = getComputerTask(buttonStates, strategy);
        computerExecutor.submit(computerMoves);
    }

    /**
     * shutdown and try to force stop tasks.
     * Note my task are not interrupt aware but that is too much thinking, and they are pretty short.
     */
    public void shutdown() {
        computerExecutor.shutdownNow();
    }
}
