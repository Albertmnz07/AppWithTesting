package domain.exceptions.password;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class PasswordMismatchException extends DomainException {

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
