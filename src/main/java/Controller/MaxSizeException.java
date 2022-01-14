package Controller;

public class MaxSizeException extends Exception{
    public MaxSizeException(String errorMessage){
        super(errorMessage);
    }
}