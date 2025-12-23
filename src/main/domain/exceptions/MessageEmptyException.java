package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class MessageEmptyException extends DomainException{

    public MessageEmptyException() {
        super(ErrorCode.MESSAGE_EMPTY);
    }
}
