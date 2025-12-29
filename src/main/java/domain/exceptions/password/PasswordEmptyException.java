package domain.exceptions.password;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class PasswordEmptyException extends DomainException {

    public PasswordEmptyException(){
        super(ErrorCode.PASSWORD_EMPTY);
    }
}
