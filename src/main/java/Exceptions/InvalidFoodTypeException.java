package Exceptions;

/**
 * Class represents a custom Exception for when an invalid foodType is delivered by the program.
 */
public class InvalidFoodTypeException extends RuntimeException{
    public InvalidFoodTypeException(String type){
        super("Invalid Type: " + type);
    }
}
