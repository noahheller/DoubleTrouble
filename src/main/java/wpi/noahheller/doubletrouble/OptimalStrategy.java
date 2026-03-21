package wpi.noahheller.doubletrouble;

import java.util.*;

public class OptimalStrategy implements GameStrategy {

    private final RandomStrategy backup = new RandomStrategy();

    @Override
    public List<Integer> pickTiles(Boolean[] tileStates) {
        Map<ButtonColor, List<Integer>> buttonColorListMap = enumerateBoard(tileStates);
         List<Integer> indexes = pickColorAndCount(buttonColorListMap);
        if (indexes.isEmpty()) {
            return backup.pickTiles(tileStates);
        }
        return indexes;
    }

    private List<Integer> pickColorAndCount(Map<ButtonColor, List<Integer>> buttonColorListMap) {
        for (ButtonColor color : buttonColorListMap.keySet()) {
            List<Integer> indexes = buttonColorListMap.get(color);
            int targetColorCount = indexes.size();
            int xOr = 0;
            for (List<Integer> value : buttonColorListMap.values()) {
                xOr ^= value.size();
            }
            //undo Xor with target
            xOr ^= targetColorCount;
            if (targetColorCount > xOr) {
                return indexes.stream().limit(targetColorCount-xOr).toList();
            }
        }
        return Collections.emptyList();
    }

    /**
     * Loops through all the colors and checks if button is available in the range specified in color.
     *
     * @param tileStates which buttons/tiles are available(true = available)
     * @return array representing how many buttons/tiles are available for each color (in the same order as declared in Color)
     */
    int[] availableColorSizes(Boolean[] tileStates) {
        ButtonColor[] colors = ButtonColor.values();
        int[] colorSizes = new int[ButtonColor.values().length];
        for (int i = 0; i < colorSizes.length; i++) {
            ButtonColor color = colors[i];
            for (int j = color.getMinButtonIndex(); j <= color.getMaxButtonIndex(); j++) {
                if (tileStates[j]) {
                    colorSizes[i]++;
                }
            }
        }
        return colorSizes;
    }

    Map<ButtonColor, List<Integer>> enumerateBoard(Boolean[] tileStates) {
        Map<ButtonColor, List<Integer>> boardState = new HashMap<>();
        ButtonColor[] colors = ButtonColor.values();
        for (ButtonColor color : colors) {
            ArrayList<Integer> indices = new ArrayList<>();
            for (int j = color.getMinButtonIndex(); j <= color.getMaxButtonIndex(); j++) {
                if (tileStates[j]) {
                    indices.add(j);
                }
            }
            boardState.put(color, indices);
        }
        return boardState;
    }
}
