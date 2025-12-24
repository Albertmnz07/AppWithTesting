package main.domain.exceptions.user;

public class UserSearchHimSelfException extends RuntimeException {
    public static final String MESSAGE = "Use cannot search for himself";

    public UserSearchHimSelfException() {
        super(MESSAGE);
    }
}
