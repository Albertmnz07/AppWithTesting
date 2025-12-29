package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserSearchHimSelfException extends DomainException {

    public UserSearchHimSelfException() {
        super(ErrorCode.USER_SEARCH_HIMSELF);
    }
}
