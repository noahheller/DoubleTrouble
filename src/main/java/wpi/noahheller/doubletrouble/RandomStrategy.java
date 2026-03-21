package wpi.noahheller.doubletrouble;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Algorithm goes as follows:
 * <ui>
 * <li>Picks a random color</li>
 * <li>checks if there are buttons available in that color</li>
 * <li>if so, return a random number of those buttons</li>
 * <li>otherwise repeat until not colors are left</li>
 * <li>If there are  colors left then the Algorithm has lost</li>
 * </ui>
 */
public class RandomStrategy implements GameStrategy {

    private final Random random = new Random();

    @Override
    public List<Integer> pickTiles(Boolean[] tileStates) {
        ButtonColor validColor = null;
        List<ButtonColor> colorsOptions = new ArrayList<>(List.of(ButtonColor.values()));
        List<Integer> indexofColoredButtons = new ArrayList<>();
        while (validColor == null && !colorsOptions.isEmpty()) {
            int colorIndex = random.nextInt(colorsOptions.size());
            // check if buttons with that color exist
            ButtonColor c = colorsOptions.get(colorIndex);
            for (int i = c.getMinButtonIndex(); i <= c.getMaxButtonIndex(); i++) {
                if (tileStates[i]) {
                    indexofColoredButtons.add(i);
                }
            }
            if (!indexofColoredButtons.isEmpty()) {
                validColor = c;
            } else {
                colorsOptions.remove(c);
            }
        }
        if (validColor == null) {
            return Collections.emptyList();
        }
        int numTilesPicked = random.nextInt(indexofColoredButtons.size()) + 1;
        return IntStream.range(0,numTilesPicked).map(indexofColoredButtons::get).boxed().toList();
    }
}
