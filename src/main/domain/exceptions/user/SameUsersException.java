package main.domain.exceptions.user;

public class SameUsersException extends RuntimeException {
    public SameUsersException(String message) {
        super(message);
    }
}
