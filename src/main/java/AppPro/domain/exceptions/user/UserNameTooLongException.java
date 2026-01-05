package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserNameTooLongException extends DomainException {

    public UserNameTooLongException(){
        super(ErrorCode.USERNAME_TOO_LONG);
    }
}
