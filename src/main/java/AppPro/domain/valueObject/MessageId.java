package AppPro.domain.valueObject;

import java.util.UUID;

/**
 * <p>Represents a message's id inside the system</p>
 * <p>This object is immutable</p>
 */
public class MessageId extends Identifier {

    /**
     * Create a new {@code MessageId}
     * @param value the id in his primitive form({@link UUID})
     */
    public MessageId(UUID messageId){
        super(messageId);
    }

    /**
     * Generates and return a new random {@link MessageId}
     * @return id in his Value Object form({@link UUID}
     */
    public static MessageId generate(){
        return new MessageId(generateValue());
    }

}
