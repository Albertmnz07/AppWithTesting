package main.infrastructure.input.cli.utils;

import main.domain.error.ErrorCode;
import main.domain.valueObject.UserName;

public class CliErrorMessage {

    public static String from(ErrorCode code){
        return switch(code){
            case USERNAME_EMPTY -> "Username can not be empty";
            case USERNAME_TOO_SHORT -> "The username is too short. Is must be between " + UserName.MIN_LENGTH + " and " + UserName.MAX_LENGTH + "Characters";
            case USERNAME_TOO_LONG -> "The username is too long Is must be between " + UserName.MIN_LENGTH + " and " + UserName.MAX_LENGTH + "Characters";
            case USER_NOT_FOUND -> "This user doesn't exists";
            case USERNAME_ALREADY_EXISTS -> "This username is not available";
            case USER_SEARCH_HIMSELF -> "You can not search for yourself";
            case USER_NOT_PARTICIPANT -> "This users does not belong to this chat";
            case SAME_USERS_IN_CHAT -> "A chat must consist in two different users";
            case MESSAGE_TOO_LONG -> "This message is too long";
            case MESSAGE_EMPTY -> "Message can not be empty";
            case ID_EMPTY -> null;
            case PASSWORD_MISMATCH -> null;
            case PASSWORD_TOO_SHORT -> null;
            case PASSWORD_EMPTY -> null;
            case PASSWORD_TOO_LONG -> null;
            case CHAT_ALREADY_EXISTS -> null;
            case CHAT_NOT_FOUND -> null;
        };
    }
}
