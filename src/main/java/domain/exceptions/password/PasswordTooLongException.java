package main.java.domain.exceptions.password;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class PasswordTooLongException extends DomainException {

    public PasswordTooLongException() {
        super(ErrorCode.PASSWORD_TOO_LONG);
    }
}
