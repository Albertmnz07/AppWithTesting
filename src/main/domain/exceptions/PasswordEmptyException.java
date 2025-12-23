package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class PasswordEmptyException extends DomainException{

    public PasswordEmptyException(){
        super(ErrorCode.PASSWORD_EMPTY);
    }
}
