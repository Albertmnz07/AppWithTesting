package main.java.domain.exceptions.password;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class PasswordEmptyException extends DomainException {

    public PasswordEmptyException(){
        super(ErrorCode.PASSWORD_EMPTY);
    }
}
