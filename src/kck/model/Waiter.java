package kck.model;

/**
 * Created by JK on 2016-10-17.
 */
public class Waiter {
    private int positionX;
    private int positionY;

    public Waiter() {
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Waiter(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
