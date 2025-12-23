package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class MessageTooLongException extends DomainException {
    public MessageTooLongException() {
        super(ErrorCode.MESSAGE_TOO_LONG);
    }
}
