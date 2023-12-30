package Environment.Food;

import Environment.IObject;
import Environment.Position;
import Exceptions.InvalidFoodTypeException;
import Management.GameManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FoodFactory{
    private static final Logger foodFactoryLogger = LogManager.getLogger(FoodFactory.class);

    /**
     * Factory which can create all types of food in the enum foodnames
     * @param foodType enum value out of the enum FoodNames
     * @param position x and y position as a Position object
     * @return IObject object in the form of a fruit
     */
    public static IObject createFood(FoodNames foodType, Position position){
        switch(foodType){
            case CHERRY -> {
                foodFactoryLogger.log(Level.DEBUG, "Creating CHERRY at X: "+position.getX()+", Y: "+position.getY());
                return new Cherry(position);
            }
            case KIWI -> {
                foodFactoryLogger.log(Level.DEBUG, "Creating KIWI at X: "+position.getX()+", Y: "+position.getY());
                return new Kiwi(position);
            }
            case APPLE -> {
                foodFactoryLogger.log(Level.DEBUG, "Creating APPLE at X: "+position.getX()+", Y: "+position.getY());
                return new Apple(position);
            }
            case MELON -> {
                foodFactoryLogger.log(Level.DEBUG, "Creating MELON at X: "+position.getX()+", Y: "+position.getY());
                return new Melon(position);
            }
            case BANANA -> {
                foodFactoryLogger.log(Level.DEBUG, "Creating BANANA at X: "+position.getX()+", Y: "+position.getY());
                return new Banana(position);
            }
            default ->{
                throw new InvalidFoodTypeException(String.valueOf(foodType));
            }
        }
    }
}
