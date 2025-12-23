package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class PasswordTooLongException extends DomainException{

    public PasswordTooLongException() {
        super(ErrorCode.PASSWORD_TOO_LONG);
    }
}
