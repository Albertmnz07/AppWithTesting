package AppPro.domain.exceptions.message;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class MessageEmptyException extends DomainException {

    public MessageEmptyException() {
        super(ErrorCode.MESSAGE_EMPTY);
    }
}
