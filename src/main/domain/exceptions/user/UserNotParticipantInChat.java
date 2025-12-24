package main.domain.exceptions.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class UserNotParticipantInChat extends DomainException {

    public UserNotParticipantInChat() {
        super(ErrorCode.USER_NOT_PARTICIPANT);
    }
}
