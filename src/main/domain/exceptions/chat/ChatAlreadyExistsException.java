package main.domain.exceptions.chat;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class ChatAlreadyExistsException extends DomainException {

    public ChatAlreadyExistsException() {
        super(ErrorCode.CHAT_ALREADY_EXISTS);
    }
}
