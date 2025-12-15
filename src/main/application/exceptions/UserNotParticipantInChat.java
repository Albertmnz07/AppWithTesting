package main.application.exceptions;

public class UserNotParticipantInChat extends RuntimeException {
    public static final String MESSAGE = "The user is not participant in the chat";
    public UserNotParticipantInChat() {
        super(MESSAGE);
    }
}
