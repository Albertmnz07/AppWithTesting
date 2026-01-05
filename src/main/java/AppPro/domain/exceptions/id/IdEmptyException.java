package AppPro.domain.exceptions.id;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class IdEmptyException extends DomainException {

    public IdEmptyException(){
        super(ErrorCode.ID_EMPTY);
    }
}
