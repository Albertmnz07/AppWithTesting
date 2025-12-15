package main.application.exceptions;

public class ChatNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Chat haven't been found";
    public ChatNotFoundException() {
        super(MESSAGE);
    }
}
