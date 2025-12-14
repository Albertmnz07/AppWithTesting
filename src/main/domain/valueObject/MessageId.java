package main.domain.valueObject;

import java.util.UUID;

public class MessageId {

    private final UUID messageId;

    public MessageId(UUID messageId){
        this.messageId = messageId;
    }

    public UUID getValue() {
        return this.messageId;
    }

    public static MessageId generate(){
        return new MessageId(UUID.randomUUID());
    }

    public static MessageId fromUUID(UUID id){
        return new MessageId(id);
    }
}
