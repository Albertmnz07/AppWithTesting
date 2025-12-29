package domain.exceptions.chat;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class ChatNotFoundException extends DomainException {

     public ChatNotFoundException() {
        super(ErrorCode.CHAT_NOT_FOUND);
    }
}
