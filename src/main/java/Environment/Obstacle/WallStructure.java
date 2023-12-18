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
    WallForms[] formsArray = WallForms.values();
    public WallStructure(int quarterNumber){
        walls[0] = new Wall(spawnFirstBlock(quarterNumber));
        form = formsArray[rnd.nextInt(0,4)];
        createStructure(form);
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
        switch(form){
            case LINE -> {
                for(int i = 1; i < walls.length;i++){
                    walls[i] = new Wall(new Position(walls[0].getPosition().getX(),walls[0].getPosition().getY()+i*GameField.SIZEBLOCK));
                }
            }
            case LSHAPE -> {
                for(int i = 1; i < walls.length-1;i++){
                    walls[i] = new Wall(new Position(walls[0].getPosition().getX()+i*GameField.SIZEBLOCK,walls[0].getPosition().getY()));
                }
                walls[walls.length-1] = new Wall(new Position(walls[2].getPosition().getX(),walls[2].getPosition().getY()+GameField.SIZEBLOCK));
            }
            case TSHAPE -> {
                for(int i = 1; i < walls.length-1;i++){
                    walls[i] = new Wall(new Position(walls[0].getPosition().getX(),walls[0].getPosition().getY()+i*GameField.SIZEBLOCK));
                }
                walls[walls.length-1] = new Wall(new Position(walls[1].getPosition().getX()+GameField.SIZEBLOCK,walls[2].getPosition().getY()));
            }
            case ZSHAPE -> {
                walls[1] = new Wall(new Position(walls[0].getPosition().getX()+GameField.SIZEBLOCK,walls[0].getPosition().getY()));
                walls[2] = new Wall(new Position(walls[1].getPosition().getX(),walls[1].getPosition().getY()+GameField.SIZEBLOCK));
                walls[3] = new Wall(new Position(walls[2].getPosition().getX()+GameField.SIZEBLOCK,walls[2].getPosition().getY()));
            }
        }
    }
}
