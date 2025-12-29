package main.java.domain.exceptions.password;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class PasswordMismatchException extends DomainException {

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
