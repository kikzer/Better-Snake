package Exceptions;

public class InvalidFoodTypeException extends RuntimeException{
    public InvalidFoodTypeException(String type){
        super("Invalid Type: " + type);
    }
}
