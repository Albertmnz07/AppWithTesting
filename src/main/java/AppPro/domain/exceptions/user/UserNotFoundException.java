package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
