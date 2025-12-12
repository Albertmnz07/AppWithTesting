package domain.valueObject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessageIdTest {

    @Test
    void shouldInstantiateMessageID(){
        MessageId id = new MessageId(UUID.randomUUID());
        assertNotNull(id);
    }

    @Test
    void shouldGetMessageIDValue(){
        UUID id = UUID.randomUUID();
        MessageId messageID = new MessageId(id);
        assertEquals(id , messageID.getValue());

    }

    @Test
    void shouldCreateMessageIDFromUUID(){
        UUID id = UUID.randomUUID();
        MessageId messageID = MessageId.fromUUID(id);


        assertEquals(id , messageID.getValue());
    }

}