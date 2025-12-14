package main.application.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "User name already exists";

    public UserNameAlreadyExistsException() {
        super(MESSAGE);
    }
}
