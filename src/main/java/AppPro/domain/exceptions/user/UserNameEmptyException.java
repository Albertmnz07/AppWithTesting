package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserNameEmptyException extends DomainException {

    public UserNameEmptyException(){
        super(ErrorCode.USERNAME_EMPTY);
    }
}
