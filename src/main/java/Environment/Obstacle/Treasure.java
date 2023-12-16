package Environment.Obstacle;

import Environment.GameField;
import Environment.AObject;
import Environment.Position;
import javafx.scene.image.Image;

public class Treasure extends AObject {



    public Treasure(Position position) {
        super(position);
        setAppearance(new Image("file:src/main/java/Environment/Obstacle/ObstacleImages/box.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }



}
