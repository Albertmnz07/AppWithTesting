package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNameTooLongException extends DomainException {

    public UserNameTooLongException(){
        super(ErrorCode.USERNAME_TOO_LONG);
    }
}
