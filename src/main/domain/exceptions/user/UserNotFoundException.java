package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
