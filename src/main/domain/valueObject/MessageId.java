package main.domain.valueObject;

import java.util.UUID;

public class MessageId extends Identifier {

    public MessageId(UUID messageId){
        super(messageId);
    }

    public static MessageId generate(){
        return new MessageId(generateValue());
    }

}
