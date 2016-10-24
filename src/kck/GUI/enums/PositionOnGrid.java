package kck.GUI.enums;

/**
 * Created by JK on 2016-10-20.
 */
public enum PositionOnGrid {
    WAITER_TABLE_ONE_POSITION(1, 2),
    WAITER_TABLE_TWO_POSITION(3, 4),
    WAITER_TABLE_THREE_POSITION(4,6),
    WAITER_KITCHEN_POSITION(1, 0),
    TABLE_ONE_POSITION(2,2),
    TABLE_TWO_POSITION(4,4),
    TABLE_THREE_POSITION(4,7),
    KITCHEN_POSITION(0,0);


    private int positionX;
    private int positionY;

    PositionOnGrid(int positionX, int positionY) {
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
