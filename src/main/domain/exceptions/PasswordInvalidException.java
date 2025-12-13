package main.domain.exceptions;

public class PasswordInvalidException extends RuntimeException{

    public PasswordInvalidException(String message){
        super(message);
    }



}
