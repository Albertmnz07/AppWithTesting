package main.domain.exceptions.password;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class PasswordTooShortException extends DomainException {

    public PasswordTooShortException(){
        super(ErrorCode.PASSWORD_TOO_SHORT);
    }



}
