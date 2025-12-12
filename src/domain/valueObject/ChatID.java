package domain.valueObject;

import java.util.UUID;

public class ChatID {

    private final UUID chatID;

    public ChatID(UUID chatID){
        this.chatID = chatID;
    }

    public UUID getValue(){
        return this.chatID;
    }

    public static ChatID fromUUID(UUID id){
        return new ChatID(id);
    }
}
