package domain.valueObject;

import java.util.UUID;

public class MessageID {

    private final UUID messageID;

    public MessageID(UUID messageID){
        this.messageID = messageID;
    }

    public UUID getValue() {
        return this.messageID;
    }

    public static MessageID fromUUID(UUID id){
        return new MessageID(id);
    }
}
