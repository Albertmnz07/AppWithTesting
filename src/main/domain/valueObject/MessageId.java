package main.domain.valueObject;

import java.util.UUID;

public class MessageId {

    private final UUID messageID;

    public MessageId(UUID messageID){
        this.messageID = messageID;
    }

    public UUID getValue() {
        return this.messageID;
    }

    public static MessageId fromUUID(UUID id){
        return new MessageId(id);
    }
}
