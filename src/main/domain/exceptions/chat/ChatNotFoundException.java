package main.domain.exceptions.chat;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class ChatNotFoundException extends DomainException {

     public ChatNotFoundException() {
        super(ErrorCode.CHAT_NOT_FOUND);
    }
}
