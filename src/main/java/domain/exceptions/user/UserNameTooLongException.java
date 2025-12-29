package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNameTooLongException extends DomainException {

    public UserNameTooLongException(){
        super(ErrorCode.USERNAME_TOO_LONG);
    }
}
