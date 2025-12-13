package domainTest.entitiesTest;

import main.domain.entities.Message;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.MessageContent;
import main.domain.valueObject.MessageId;
import main.domain.valueObject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageTest {

    private Message message;
    private UserId senderId;
    private MessageId messageID;
    private MessageContent messageContent;
    private ChatId chatID;

    @BeforeEach
    void setUp(){
        senderId = new UserId();
        messageID = new MessageId(UUID.randomUUID());
        messageContent = new MessageContent("Message content");
        chatID = new ChatId(UUID.randomUUID());
        message = new Message(senderId, messageID , messageContent , chatID);
    }

    @Test
    void shouldInstantiateMessage(){
        assertNotNull(message);
    }

    @Test
    void shouldGetUserId(){
        assertEquals(senderId, message.getSenderId());
    }

    @Test
    void shouldGetMessageId(){
        assertEquals(messageID , message.getMessageID());
    }

    @Test
    void shouldGetChatID(){
        assertEquals(chatID , message.getChatID());
    }

    @Test
    void shouldGetMessageContent(){
        assertEquals(messageContent , message.getMessageContent());
    }

    @Test
    void updateMessageContent(){
        MessageContent newMessageContent = new MessageContent("new content");

        message.updateMessageContent(newMessageContent);

        assertEquals(newMessageContent , message.getMessageContent());
    }

}