package domain.valueObject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessageIDTest {

    @Test
    void shouldInstantiateMessageID(){
        MessageID id = new MessageID(UUID.randomUUID());
        assertNotNull(id);
    }

    @Test
    void shouldGetMessageIDValue(){
        UUID id = UUID.randomUUID();
        MessageID messageID = new MessageID(id);
        assertEquals(id , messageID.getValue());

    }

    @Test
    void shouldCreateMessageIDFromUUID(){
        UUID id = UUID.randomUUID();
        MessageID messageID = MessageID.fromUUID(id);


        assertEquals(id , messageID.getValue());
    }

}