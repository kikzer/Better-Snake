package Managment;

import javax.net.ssl.KeyManager;
import java.util.ArrayList;

public class Snake {

    private final ArrayList<Integer>xPositions = new ArrayList<>(),yPositions = new ArrayList<>();
    //Pos 0 = up, Pos 1 = down, Pos 2 = right, Pos 3 = left
    private final int[] directionX = {0,0,1,-1};
    private final int[] directionY = {-1,1,0,0};

    private int direction = 0;

    public Snake(final int startPositionX, final int startPositionY){
        getXPositions().add(startPositionX);
        getYPositions().add(startPositionY);
    }

    public void move(){
        getXPositions().add(0, getXPositions().get(0) + getDirectionX()[getDirection()]);
        getYPositions().add(0, getYPositions().get(0) + getDirectionY()[getDirection()]);

        getXPositions().remove(getXPositions().size()-1);
        getYPositions().remove(getYPositions().size()-1);
    }

    public ArrayList<Integer> getXPositions() {
        return xPositions;
    }

    public int[] getDirectionX() {
        return directionX;
    }

    public int[] getDirectionY() {
        return directionY;
    }

    public ArrayList<Integer> getYPositions() {
        return yPositions;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public class KeyFunction {

    }
}
