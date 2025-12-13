package main.domain.exceptions;

public class MessageContentInvalidException extends RuntimeException {
    public MessageContentInvalidException(String message) {
        super(message);
    }
}
