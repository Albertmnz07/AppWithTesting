package main.java.domain.exceptions.message;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class MessageEmptyException extends DomainException {

    public MessageEmptyException() {
        super(ErrorCode.MESSAGE_EMPTY);
    }
}
