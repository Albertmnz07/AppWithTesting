package main.domain.exceptions;

import main.domain.error.ErrorCode;

public class IdEmptyException extends DomainException{

    public IdEmptyException(){
        super(ErrorCode.ID_EMPTY);
    }
}
