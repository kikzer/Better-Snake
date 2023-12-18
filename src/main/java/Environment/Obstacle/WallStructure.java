package Environment.Obstacle;

import Environment.GameField;
import Environment.Position;
import Management.Interface.GameWindow;

import java.util.ArrayList;
import java.util.Random;

public class WallStructure {
    WallForms form;
    Random rnd = new Random();
    Wall[] walls = new Wall[4];
    public WallStructure(int quarterNumber){

    }
    private Position spawnFirstBlock(int quarterNumber){
        switch(quarterNumber){
            case 1 ->{
                return new Position(8,8);
            }
            case 2 ->{
                return new Position(17,8);
            }
            case 3 ->{
                return new Position(17,17);
            }
            case 4 ->{
                return new Position(8,17);
            }
            default->{
                return new Position(8,8);
            }
        }
    }

    private void createStructure(WallForms form){

    }
}
