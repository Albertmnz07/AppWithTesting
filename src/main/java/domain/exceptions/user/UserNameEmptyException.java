package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNameEmptyException extends DomainException {

    public UserNameEmptyException(){
        super(ErrorCode.USERNAME_EMPTY);
    }
}
