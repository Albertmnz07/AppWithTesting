package AppPro.domain.entities;

import AppPro.domain.exceptions.user.SameUsersException;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.UserId;

public class Chat {

    private final UserId userA;
    private final UserId userB;
    private final ChatId chatId;

    public static final String SAME_USER_ERROR = "Both users in chat must be different";

    public Chat(UserId userA , UserId userB , ChatId chatId){

        if (userA.equals(userB)){
            throw new SameUsersException();
        }

        this.userA = userA;
        this.userB = userB;
        this.chatId = chatId;
    }

    public UserId getUserA(){
        return userA;
    }

    public UserId getUserB(){
        return userB;
    }

    public ChatId getChatId(){
        return chatId;
    }

    public boolean involves(UserId user){
        return userA.equals(user) || userB.equals(user);
    }

}