package Environment;

/**
 * This class represents the coordinate calculation of the game Better Snake.
 * It needs an x and y position to make a 2D location
 */
public class Position {
    private int x,y;

    public Position(final int x, final int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
