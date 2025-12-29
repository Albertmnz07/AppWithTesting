package domain.exceptions.user;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class UserNotParticipantInChat extends DomainException {

    public UserNotParticipantInChat() {
        super(ErrorCode.USER_NOT_PARTICIPANT);
    }
}
