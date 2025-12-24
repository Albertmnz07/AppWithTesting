package main.domain.exceptions.password;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class PasswordEmptyException extends DomainException {

    public PasswordEmptyException(){
        super(ErrorCode.PASSWORD_EMPTY);
    }
}
