package AppPro.domain.exceptions.password;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class PasswordEmptyException extends DomainException {

    public PasswordEmptyException(){
        super(ErrorCode.PASSWORD_EMPTY);
    }
}
