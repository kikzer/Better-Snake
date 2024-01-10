package Environment.Food;

import Environment.AObject;
import Environment.Position;


public class Apple extends AObject {

    public Apple(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/FoodImages/apple.png");
    }
}
