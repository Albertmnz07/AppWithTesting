package domain.valueObject;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChatIDTest {

    @Test
    void shouldInstantiateChatID(){
        ChatID chatID = new ChatID(UUID.randomUUID());
        assertNotNull(chatID);
    }

    @Test
    void shouldGetChatIDValue(){
        UUID id = UUID.randomUUID();
        ChatID chatID = new ChatID(id);
        assertEquals(id , chatID.getValue());
    }

    @Test
    void shouldCreateMessageFromUUID(){
        UUID id = UUID.randomUUID();
        ChatID chatID = ChatID.fromUUID(id);
        assertEquals(id , chatID.getValue());

    }

}