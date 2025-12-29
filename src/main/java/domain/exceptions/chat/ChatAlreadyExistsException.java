package domain.exceptions.chat;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class ChatAlreadyExistsException extends DomainException {

    public ChatAlreadyExistsException() {
        super(ErrorCode.CHAT_ALREADY_EXISTS);
    }
}
