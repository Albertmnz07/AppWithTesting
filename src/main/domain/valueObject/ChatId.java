package main.domain.valueObject;

import java.util.UUID;

public class ChatId extends Identifier{

    public ChatId(UUID value){
        super(value);
    }

    public static ChatId generate(){
        return new ChatId(generateValue());
    }
}
