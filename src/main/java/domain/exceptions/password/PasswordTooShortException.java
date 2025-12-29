package domain.exceptions.password;

import domain.error.ErrorCode;
import domain.exceptions.DomainException;

public class PasswordTooShortException extends DomainException {

    public PasswordTooShortException(){
        super(ErrorCode.PASSWORD_TOO_SHORT);
    }



}
