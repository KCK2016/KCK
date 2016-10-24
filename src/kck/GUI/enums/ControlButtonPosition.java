package kck.GUI.enums;

/**
 * Created by JK on 2016-10-20.
 */
public enum ControlButtonPosition {
    FIRSTBUTTON(9, 0),
    SECONDBUTTON(9, 2),
    THIRDBUTTON(9, 4),
    FOURFBUTTON(9,6),
    TEXTBUTTON(9, 9),
    TEXT_AREA_POSTION(0, 9);

    private int positionX;
    private int positionY;

    ControlButtonPosition(int positionX, int positionY) {
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
