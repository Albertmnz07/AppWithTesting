package main.domain.exceptions.id;

import main.domain.error.ErrorCode;
import main.domain.exceptions.DomainException;

public class IdEmptyException extends DomainException {

    public IdEmptyException(){
        super(ErrorCode.ID_EMPTY);
    }
}
