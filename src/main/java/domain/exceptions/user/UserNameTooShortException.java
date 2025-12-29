package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNameTooShortException extends DomainException {

    public UserNameTooShortException(){super(ErrorCode.USERNAME_TOO_SHORT);}
}
