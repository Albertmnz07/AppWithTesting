package AppPro.domain.exceptions.password;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class PasswordTooShortException extends DomainException {

    public PasswordTooShortException(){
        super(ErrorCode.PASSWORD_TOO_SHORT);
    }



}
