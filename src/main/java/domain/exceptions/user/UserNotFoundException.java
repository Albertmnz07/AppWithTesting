package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
