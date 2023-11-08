package Management.SnakeManagement;

import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {

    private final ArrayList<Integer>xPositions = new ArrayList<>(),yPositions = new ArrayList<>();

    //Pos 0 = up, Pos 1 = down, Pos 2 = right, Pos 3 = left
    private final int[] directionX = {0,0,1,-1};
    private final int[] directionY = {-1,1,0,0};

    private int direction = 0;

    private Directions directionEnum  = Directions.UP;

    public Snake(final int startPositionX, final int startPositionY){
        getXPositions().add(startPositionX);
        getYPositions().add(startPositionY);
    }

    private void decideDirections(){
        switch (getDirectionEnum()){
            case UP -> {
                if(getDirections() != 1){
                    setDirection(0);
                }
            }
            case DOWN -> {
                if(getDirections() != 0){
                    setDirection(1);
                }
            }
            case LEFT -> {
                if(getDirections() != 2){
                    setDirection(3);
                }
            }
            case RIGHT -> {
                if(getDirections() != 3){
                    setDirection(2);
                }
            }
        }
    }

    public void move(){
        decideDirections();
        getXPositions().add(0, getXPositions().get(0) + getDirectionX()[getDirections()]);
        getYPositions().add(0, getYPositions().get(0) + getDirectionY()[getDirections()]);

        getXPositions().remove(getXPositions().size()-1);
        getYPositions().remove(getYPositions().size()-1);
    }

    public Directions getDirectionEnum() {
        return directionEnum;
    }

    public void setDirectionEnum(Directions directions) {
        directionEnum = directions;
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

    public int getDirections() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public class KeyFunction extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_DOWN -> setDirectionEnum(Directions.DOWN);
                case KeyEvent.VK_UP -> setDirectionEnum(Directions.UP);
                case KeyEvent.VK_LEFT -> setDirectionEnum(Directions.LEFT);
                case KeyEvent.VK_RIGHT -> setDirectionEnum(Directions.RIGHT);
            }
        }
    }
}
