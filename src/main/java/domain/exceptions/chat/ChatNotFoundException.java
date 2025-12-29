package main.java.domain.exceptions.chat;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class ChatNotFoundException extends DomainException {

     public ChatNotFoundException() {
        super(ErrorCode.CHAT_NOT_FOUND);
    }
}
