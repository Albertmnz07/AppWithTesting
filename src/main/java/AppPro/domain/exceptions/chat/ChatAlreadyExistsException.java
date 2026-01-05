package AppPro.domain.exceptions.chat;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class ChatAlreadyExistsException extends DomainException {

    public ChatAlreadyExistsException() {
        super(ErrorCode.CHAT_ALREADY_EXISTS);
    }
}
