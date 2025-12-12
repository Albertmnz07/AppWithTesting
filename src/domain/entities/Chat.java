package domain.entities;

import domain.exceptions.SameUsersException;
import domain.valueObject.ChatID;

public class Chat {

    private final User userA;
    private final User userB;
    private final ChatID chatID;

    public static final String SAME_USER_ERROR = "Both users in chat must be different";

    public Chat(User userA , User userB , ChatID chatID){

        if (userA.equals(userB)){
            throw new SameUsersException(SAME_USER_ERROR);
        }

        this.userA = userA;
        this.userB = userB;
        this.chatID = chatID;
    }

    public User getUserA(){
        return userA;
    }

    public User getUserB(){
        return userB;
    }

    public ChatID getChatID(){
        return chatID;
    }

}