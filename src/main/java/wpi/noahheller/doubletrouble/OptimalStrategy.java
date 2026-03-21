package wpi.noahheller.doubletrouble;

import java.util.*;

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
