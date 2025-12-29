package main.java.domain.exceptions.id;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.DomainException;

public class IdEmptyException extends DomainException {

    public IdEmptyException(){
        super(ErrorCode.ID_EMPTY);
    }
}
