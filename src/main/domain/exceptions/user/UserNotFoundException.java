package main.domain.exceptions.user;

public class UserNotFoundException extends RuntimeException {

    public static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
