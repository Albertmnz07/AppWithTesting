package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserNameTooShortException extends DomainException {

    public UserNameTooShortException(){super(ErrorCode.USERNAME_TOO_SHORT);}
}
