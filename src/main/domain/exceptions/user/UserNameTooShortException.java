package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNameTooShortException extends DomainException {

    public UserNameTooShortException(){super(ErrorCode.USERNAME_TOO_SHORT);}
}
