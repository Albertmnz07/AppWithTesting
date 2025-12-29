package main.java.domain.exceptions.password;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class PasswordTooShortException extends DomainException {

    public PasswordTooShortException(){
        super(ErrorCode.PASSWORD_TOO_SHORT);
    }



}
