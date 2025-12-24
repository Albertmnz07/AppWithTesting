package main.domain.exceptions.password;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class PasswordMismatchException extends DomainException {

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
