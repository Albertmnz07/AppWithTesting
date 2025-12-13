package main.domain.valueObject;

import java.util.UUID;

public class ChatId {

    private final UUID chatID;

    public ChatId(UUID chatID){
        this.chatID = chatID;
    }

    public UUID getValue(){
        return this.chatID;
    }

    public static ChatId fromUUID(UUID id){
        return new ChatId(id);
    }
}
