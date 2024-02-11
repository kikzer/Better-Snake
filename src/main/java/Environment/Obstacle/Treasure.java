package Environment.Obstacle;

import Environment.AObject;
import Environment.Position;

/**
 * a treasure box which can be placed in a 2D Environment using the Position class
 */
public class Treasure extends AObject {


    public Treasure(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/ObstacleImages/box.png");
    }
}
