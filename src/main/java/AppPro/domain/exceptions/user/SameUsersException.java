package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class SameUsersException extends DomainException {
    public SameUsersException() {
        super(ErrorCode.SAME_USERS_IN_CHAT);
    }
}
