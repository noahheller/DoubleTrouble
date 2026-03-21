package wpi.noahheller.doubletrouble;

import org.jetbrains.annotations.Nullable;

public enum ButtonColor {
    GREEN(0, 2), YELLOW(3, 9), RED(10, 14);

    private final int minButtonIndex;
    private final int maxButtonIndex;

    ButtonColor(int minButtonIndex, int maxButtonIndex) {
        this.minButtonIndex = minButtonIndex;
        this.maxButtonIndex = maxButtonIndex;
    }

    public int getMinButtonIndex() {
        return minButtonIndex;
    }

    public int getMaxButtonIndex() {
        return maxButtonIndex;
    }
    @Nullable
    public static ButtonColor buttonIndexToColor(int index){
        //out of bounds
        if (index < GREEN.minButtonIndex || index > RED.maxButtonIndex){
            return null;
        }
        if (index <= GREEN.maxButtonIndex){
            return ButtonColor.GREEN;
        }
        if (index <= YELLOW.maxButtonIndex){
            return ButtonColor.YELLOW;
        }
        return ButtonColor.RED;
    }
}
