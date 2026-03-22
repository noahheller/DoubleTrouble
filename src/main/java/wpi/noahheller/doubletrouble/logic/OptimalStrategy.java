package wpi.noahheller.doubletrouble.logic;

import wpi.noahheller.doubletrouble.ButtonColor;

import java.util.*;

/**
 * Algorithm that tries to make the NIM-sum = 0 at then end of its turn.
 * The NIM-sum is found by Xor-ing the number of tiles available in each pile.
 */
public class OptimalStrategy implements GameStrategy {

    private final RandomStrategy backup = new RandomStrategy();

    @Override
    public List<Integer> pickTiles(Boolean[] tileStates) {
        ButtonColor[] colors = ButtonColor.values();
        int[] counts = new int[colors.length];
        for (int i = 0; i < colors.length; i++) {
            ButtonColor color = colors[i];
            for (int j = color.getMinButtonIndex(); j <= color.getMaxButtonIndex(); j++) {
                if (tileStates[j]) {
                    counts[i]++;
                }
            }
        }
        int xor = 0;
        for (int count : counts) {
            xor ^= count;
        }
        for (int i = 0; i < colors.length; i++) {
            int count = counts[i];
            int target = count ^ xor;
            if (count > target) {
                int removeCount = count - target;
                return collectIndices(tileStates, colors[i], removeCount);
            }
        }
        return backup.pickTiles(tileStates);
    }

    /**
     * Chooses {@code limit} number of buttons from {@code tileState} array that are available.
     *<br>
     * It is up to the color to unsure {@code limit} number is available,
     * otherwise it will return maximum number of tileStates that are available
     * @param tileStates Complete list of tiles/buttons. Buttons that are available will have a value of true
     * @param color      which color the computer is choosing
     * @param limit      how many of said color we are choosing
     * @return List of indices of the buttons/tiles we are clicking
     */
    private List<Integer> collectIndices(Boolean[] tileStates, ButtonColor color, int limit) {
        List<Integer> result = new ArrayList<>(limit);
        for (int j = color.getMinButtonIndex(); j <= color.getMaxButtonIndex() && result.size() < limit; j++) {
            if (tileStates[j]) {
                result.add(j);
            }
        }
        return result;
    }
}
