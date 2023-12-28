package Environment.Obstacle.ObstacleForms;

import Environment.GameField;
import Environment.AObject;
import Environment.Position;
import javafx.scene.image.Image;

public class Wall extends AObject {

    public Wall(Position position) {
        super(position);
        setBlocked(true);
        setAppearance(new Image("file:src/main/java/Environment/Obstacle/ObstacleImages/wallBlock.png", GameField.SIZEBLOCK, GameField.SIZEBLOCK,true,true));
    }
}
