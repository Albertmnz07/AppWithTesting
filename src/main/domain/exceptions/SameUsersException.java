package main.domain.exceptions;

public class SameUsersException extends RuntimeException {
    public SameUsersException(String message) {
        super(message);
    }
}
