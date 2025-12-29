package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class SameUsersException extends DomainException {
    public SameUsersException() {
        super(ErrorCode.SAME_USERS_IN_CHAT);
    }
}
