package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserSearchHimSelfException extends DomainException {

    public UserSearchHimSelfException() {
        super(ErrorCode.USER_SEARCH_HIMSELF);
    }
}
