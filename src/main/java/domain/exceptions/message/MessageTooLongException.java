package main.java.domain.exceptions.message;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class MessageTooLongException extends DomainException {
    public MessageTooLongException() {
        super(ErrorCode.MESSAGE_TOO_LONG);
    }
}
