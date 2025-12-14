package domain.valueObject;

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
        ChatId chatId = new ChatId(id);
        assertEquals(id , chatId.getValue());
    }

}