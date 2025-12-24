package main.domain.exceptions.password;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class PasswordTooLongException extends DomainException {

    public PasswordTooLongException() {
        super(ErrorCode.PASSWORD_TOO_LONG);
    }
}
