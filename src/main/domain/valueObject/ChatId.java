package main.domain.valueObject;

import java.util.UUID;

public class ChatId {

    private final UUID chatId;

    public ChatId(UUID value){
        this.chatId = value;
    }

    public static ChatId generate(){
        return new ChatId(UUID.randomUUID());
    }

    public UUID getValue(){
        return this.chatId;
    }
}
