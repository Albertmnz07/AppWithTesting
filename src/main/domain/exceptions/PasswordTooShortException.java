package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class PasswordTooShortException extends DomainException{

    public PasswordTooShortException(){
        super(ErrorCode.PASSWORD_TOO_SHORT);
    }



}
