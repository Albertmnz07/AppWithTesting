package domain.exceptions.id;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class IdEmptyException extends DomainException {

    public IdEmptyException(){
        super(ErrorCode.ID_EMPTY);
    }
}
