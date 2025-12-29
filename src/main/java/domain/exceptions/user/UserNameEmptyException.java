package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNameEmptyException extends DomainException {

    public UserNameEmptyException(){
        super(ErrorCode.USERNAME_EMPTY);
    }
}
