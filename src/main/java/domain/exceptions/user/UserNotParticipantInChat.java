package main.java.domain.exceptions.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class UserNotParticipantInChat extends DomainException {

    public UserNotParticipantInChat() {
        super(ErrorCode.USER_NOT_PARTICIPANT);
    }
}
