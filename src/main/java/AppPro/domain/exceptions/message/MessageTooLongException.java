package AppPro.domain.exceptions.message;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.DomainException;

public class MessageTooLongException extends DomainException {
    public MessageTooLongException() {
        super(ErrorCode.MESSAGE_TOO_LONG);
    }
}
