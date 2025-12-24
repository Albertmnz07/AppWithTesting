package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNameAlreadyExistsException extends DomainException {

    public UserNameAlreadyExistsException() {
        super(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
}
