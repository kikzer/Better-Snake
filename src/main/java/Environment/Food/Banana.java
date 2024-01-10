package Environment.Food;

import Environment.AObject;
import Environment.Position;

public class Banana extends AObject {

    public Banana(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/FoodImages/banana.png");
    }
}
