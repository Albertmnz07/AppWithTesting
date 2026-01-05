package AppPro.domain.exceptions;

import AppPro.domain.error.ErrorCode;

public abstract class DomainException extends RuntimeException {

    private final ErrorCode code;

    public DomainException(ErrorCode code){
        this.code = code;
    }

    public ErrorCode getCode(){
        return this.code;
    }
}
