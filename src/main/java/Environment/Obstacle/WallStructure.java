package Environment.Obstacle;

import Environment.GameField;
import Environment.IWallStructure;
import Environment.Position;
import Management.Interface.GameWindow;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

public class WallStructure implements IWallStructure {
    WallForms form;
    Random rnd = new Random();
    Wall[] walls = new Wall[4];
    WallForms[] formsArray = WallForms.values();
    private static final Logger wallstructureLogger = LogManager.getLogger(WallStructure.class);
    public WallStructure(int quarterNumber){
        walls[0] = new Wall(spawnFirstBlock(quarterNumber));
        form = formsArray[rnd.nextInt(0,4)];
        createStructure(form);
        wallstructureLogger.log(Level.DEBUG, "Created Wallstructure Object");
    }
    public Position spawnFirstBlock(int quarterNumber){
        switch(quarterNumber){
            case 1 ->{
                return new Position(8*GameField.SIZEBLOCK,8*GameField.SIZEBLOCK);
            }
            case 2 ->{
                return new Position(17*GameField.SIZEBLOCK,8*GameField.SIZEBLOCK);
            }
            case 3 ->{
                return new Position(17*GameField.SIZEBLOCK,17*GameField.SIZEBLOCK);
            }
            case 4 ->{
                return new Position(8*GameField.SIZEBLOCK,17*GameField.SIZEBLOCK);
            }
            default->{
                return null;
            }
        }
    }
    public void createStructure(WallForms form){
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
        for(Wall wall : walls){
            wallstructureLogger.log(Level.DEBUG, "Wall created at X: "+wall.getPosition().getX()+", Y: "+wall.getPosition().getY());
        }
    }

    @Override
    public void showStructure() {
        for (Wall wall : walls) {
            wall.show(GameWindow.getInstance().getGraphicContext());
        }
    }

    public WallForms getForm() {
        return form;
    }

    public void setForm(WallForms form) {
        this.form = form;
    }

    @Override
    public Wall[] getWalls() {
        return walls;
    }

    public void setWalls(Wall[] walls) {
        this.walls = walls;
    }
}
