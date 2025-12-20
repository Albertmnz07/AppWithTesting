package main.domain.exceptions;

public class IdEmptyException extends RuntimeException{

    public static final String MESSAGE = "Id can not be empty or null";

    public IdEmptyException(){
        super(MESSAGE);
    }
}
