package main.domain.exceptions.message;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class MessageTooLongException extends DomainException {
    public MessageTooLongException() {
        super(ErrorCode.MESSAGE_TOO_LONG);
    }
}
