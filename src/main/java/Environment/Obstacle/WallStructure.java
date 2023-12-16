package Environment.Obstacle;

import Environment.GameField;
import Management.Interface.GameWindow;

import java.util.ArrayList;
import java.util.Random;

public class WallStructure {
    WallForms form;
    Random rnd = new Random();
    Wall[] walls = new Wall[4];
    private int randomCoordinate(){
        return rnd.nextInt(4, (GameWindow.WIDTH / GameField.SIZEBLOCK)-4) * GameField.SIZEBLOCK;
    }
}
