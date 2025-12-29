package main.java.domain.valueObject;

import java.util.UUID;

/**
 * <p>Represents a chat's id inside the system</p>
 * <p>This object is immutable</p>
 */
public class ChatId extends Identifier{

    /**
     * Create a new {@code ChatId}
     * @param value the id in his primitive form({@link UUID})
     */
    public ChatId(UUID value){
        super(value);
    }

    /**
     * Generates and return a new random {@link ChatId}
     * @return id in his Value Object form({@link UUID}
     */
    public static ChatId generate(){
        return new ChatId(generateValue());
    }
}
