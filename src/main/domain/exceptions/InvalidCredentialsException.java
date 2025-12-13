package main.domain.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public static final String MESSAGE = "Invalid Password";
    public InvalidCredentialsException() {
        super(MESSAGE);
    }
}
