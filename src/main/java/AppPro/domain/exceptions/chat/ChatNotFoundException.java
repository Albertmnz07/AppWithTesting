package AppPro.domain.exceptions.chat;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class ChatNotFoundException extends DomainException {

     public ChatNotFoundException() {
        super(ErrorCode.CHAT_NOT_FOUND);
    }
}
