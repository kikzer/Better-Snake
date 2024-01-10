package Environment.Food;

import Environment.AObject;
import Environment.Position;

public class Cherry extends AObject {

    public Cherry(Position position) {
        super(position);
        setAppearance("file:src/main/java/Environment/Images/FoodImages/cherry.png");
    }
}
