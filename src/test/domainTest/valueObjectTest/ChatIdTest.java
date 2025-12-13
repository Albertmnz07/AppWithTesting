package domainTest.valueObjectTest;

import main.domain.valueObject.ChatId;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChatIdTest {

    @Test
    void shouldInstantiateChatID(){
        ChatId chatID = new ChatId(UUID.randomUUID());
        assertNotNull(chatID);
    }

    @Test
    void shouldGetChatIDValue(){
        UUID id = UUID.randomUUID();
        ChatId chatID = new ChatId(id);
        assertEquals(id , chatID.getValue());
    }

    @Test
    void shouldCreateMessageFromUUID(){
        UUID id = UUID.randomUUID();
        ChatId chatID = ChatId.fromUUID(id);
        assertEquals(id , chatID.getValue());

    }

}