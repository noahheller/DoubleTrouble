package wpi.noahheller.doubletrouble;

import java.util.List;

public interface GameStrategy {

    /**
     * @param tilesStates boolean array representing all the tiles states. True means available, false means unavailable
     * @return List of tiles Indexes to pick
     */
    List<Integer> pickTiles(Boolean[] tilesStates);
}
