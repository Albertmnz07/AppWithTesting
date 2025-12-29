package main.java.domain.exceptions.chat;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class ChatAlreadyExistsException extends DomainException {

    public ChatAlreadyExistsException() {
        super(ErrorCode.CHAT_ALREADY_EXISTS);
    }
}
