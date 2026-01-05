package AppPro.domain.exceptions.password;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class PasswordTooLongException extends DomainException {

    public PasswordTooLongException() {
        super(ErrorCode.PASSWORD_TOO_LONG);
    }
}
