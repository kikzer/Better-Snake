package Environment;

import Environment.Food.FoodNames;

public interface IObjectFactory {
    static IObject createFood(FoodNames foodType, Position position) {
        return null;
    }
}
