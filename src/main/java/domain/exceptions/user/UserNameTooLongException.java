package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNameTooLongException extends DomainException {

    public UserNameTooLongException(){
        super(ErrorCode.USERNAME_TOO_LONG);
    }
}
