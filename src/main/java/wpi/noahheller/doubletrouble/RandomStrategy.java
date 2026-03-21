package wpi.noahheller.doubletrouble;

import java.util.*;
import java.util.stream.IntStream;

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
