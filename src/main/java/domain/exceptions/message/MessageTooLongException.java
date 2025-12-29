package domain.exceptions.message;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class MessageTooLongException extends DomainException {
    public MessageTooLongException() {
        super(ErrorCode.MESSAGE_TOO_LONG);
    }
}
