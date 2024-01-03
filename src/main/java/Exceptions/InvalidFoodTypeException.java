package Exceptions;

/**
 * Class represents a custom Exception for when an invald foodType is delivered by the program.
 */
public class InvalidFoodTypeException extends RuntimeException{
    public InvalidFoodTypeException(String type){
        super("Invalid Type: " + type);
    }
}
