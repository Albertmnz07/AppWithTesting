package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserSearchHimSelfException extends DomainException {

    public UserSearchHimSelfException() {
        super(ErrorCode.USER_SEARCH_HIMSELF);
    }
}
