package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserSearchHimSelfException extends DomainException {

    public UserSearchHimSelfException() {
        super(ErrorCode.USER_SEARCH_HIMSELF);
    }
}
