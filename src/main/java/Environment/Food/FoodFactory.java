package Environment.Food;

import Environment.IObject;
import Environment.IObjectFactory;
import Environment.Position;

public class FoodFactory implements IObjectFactory {

    public static IObject createFood(FoodNames foodType, Position position){
        switch(foodType){
            case CHERRY -> {
                return new Cherry(position);
            }
            case KIWI -> {
                return new Kiwi(position);
            }
            case APPLE -> {
                return new Apple(position);
            }
            case MELON -> {
                return new Melon(position);
            }
            case BANANA -> {
                return new Banana(position);
            }
            default ->{
                return null;
            }
        }
    }
}
