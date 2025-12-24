package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class PasswordMismatchException extends DomainException {

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
