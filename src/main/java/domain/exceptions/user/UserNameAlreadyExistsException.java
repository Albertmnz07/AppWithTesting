package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNameAlreadyExistsException extends DomainException {

    public UserNameAlreadyExistsException() {
        super(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
}
