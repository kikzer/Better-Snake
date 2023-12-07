package GameField.Food;

import GameField.IObject;

public class FoodFactory {

    public static IObject createFood(FoodNames foodType, int x, int y){
        switch(foodType){
            case CHERRY -> {
                return new Cherry(x,y);
            }
            case KIWI -> {
                return new Kiwi(x,y);
            }
            case APPLE -> {
                return new Apple(x,y);
            }
            case MELON -> {
                return new Melon(x,y);
            }
            case BANANA -> {
                return new Banana(x,y);
            }
            default ->{
                return null;
            }
        }
    }
}
