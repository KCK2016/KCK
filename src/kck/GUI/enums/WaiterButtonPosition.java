package kck.GUI.enums;

/**
 * Created by JK on 2016-10-20.
 */
public enum WaiterButtonPosition {
    FIRSTBUTTON(9, 0),
    SECONDBUTTON(9, 2),
    THIRDBUTTON(9, 4),
    FOURFBUTTON(9,6);

    private int positionX;
    private int positionY;

    WaiterButtonPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}
