package main.domain.exceptions.chat;

public class ChatAlreadyExistsException extends RuntimeException {

    public static final String MESSAGE = "There is already a chat between this two users";
    public ChatAlreadyExistsException() {
        super(MESSAGE);
    }
}
