package domain.exceptions.message;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class MessageEmptyException extends DomainException {

    public MessageEmptyException() {
        super(ErrorCode.MESSAGE_EMPTY);
    }
}
