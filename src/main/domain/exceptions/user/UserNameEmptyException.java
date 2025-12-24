package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNameEmptyException extends DomainException {

    public UserNameEmptyException(){
        super(ErrorCode.USERNAME_EMPTY);
    }
}
