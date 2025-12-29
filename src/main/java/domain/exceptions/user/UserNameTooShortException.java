package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNameTooShortException extends DomainException {

    public UserNameTooShortException(){super(ErrorCode.USERNAME_TOO_SHORT);}
}
