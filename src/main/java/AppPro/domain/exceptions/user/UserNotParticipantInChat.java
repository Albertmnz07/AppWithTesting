package AppPro.domain.exceptions.user;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class UserNotParticipantInChat extends DomainException {

    public UserNotParticipantInChat() {
        super(ErrorCode.USER_NOT_PARTICIPANT);
    }
}
