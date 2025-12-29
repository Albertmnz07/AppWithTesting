package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNameAlreadyExistsException extends DomainException {

    public UserNameAlreadyExistsException() {
        super(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
}
