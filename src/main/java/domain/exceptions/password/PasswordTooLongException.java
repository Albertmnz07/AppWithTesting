package domain.exceptions.password;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class PasswordTooLongException extends DomainException {

    public PasswordTooLongException() {
        super(ErrorCode.PASSWORD_TOO_LONG);
    }
}
