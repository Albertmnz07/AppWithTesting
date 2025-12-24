package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class SameUsersException extends DomainException {
    public SameUsersException() {
        super(ErrorCode.SAME_USERS_IN_CHAT);
    }
}
