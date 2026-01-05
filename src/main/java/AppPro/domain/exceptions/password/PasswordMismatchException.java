package AppPro.domain.exceptions.password;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class PasswordMismatchException extends DomainException {

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
