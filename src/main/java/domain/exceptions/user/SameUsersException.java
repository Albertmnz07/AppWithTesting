package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class SameUsersException extends DomainException {
    public SameUsersException() {
        super(ErrorCode.SAME_USERS_IN_CHAT);
    }
}
