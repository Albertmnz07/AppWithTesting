package main.domain.exceptions.message;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class MessageEmptyException extends DomainException {

    public MessageEmptyException() {
        super(ErrorCode.MESSAGE_EMPTY);
    }
}
