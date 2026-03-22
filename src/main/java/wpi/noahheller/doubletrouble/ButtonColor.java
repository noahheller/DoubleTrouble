package wpi.noahheller.doubletrouble;

import org.jetbrains.annotations.Nullable;

public enum ButtonColor {
    GREEN(0, 2, "greenButton"), YELLOW(3, 9, "yellowButton"), RED(10, 14, "redButton");

    private final int minButtonIndex;
    private final int maxButtonIndex;
    private final String styleClassName;

    ButtonColor(int minButtonIndex, int maxButtonIndex, String styleClassName) {
        this.minButtonIndex = minButtonIndex;
        this.maxButtonIndex = maxButtonIndex;
        this.styleClassName = styleClassName;
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

    public String getStyleClassName() {
        return styleClassName;
    }
}
