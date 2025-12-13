package main.domain.entities;

import main.domain.exceptions.SameUsersException;
import main.domain.valueObject.ChatId;

public class Chat {

    private final User userA;
    private final User userB;
    private final ChatId chatId;

    public static final String SAME_USER_ERROR = "Both users in chat must be different";

    public Chat(User userA , User userB , ChatId chatId){

        if (userA.equals(userB)){
            throw new SameUsersException(SAME_USER_ERROR);
        }

        this.userA = userA;
        this.userB = userB;
        this.chatId = chatId;
    }

    public User getUserA(){
        return userA;
    }

    public User getUserB(){
        return userB;
    }

    public ChatId getChatId(){
        return chatId;
    }

}