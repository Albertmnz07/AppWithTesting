package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
