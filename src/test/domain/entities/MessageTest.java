package domain.entities;

import domain.valueObject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private Message message;
    private UserId senderId;
    private MessageID messageID;
    private MessageContent messageContent;
    private ChatID chatID;

    @BeforeEach
    void setUp(){
        senderId = new UserId(UUID.randomUUID());
        messageID = new MessageID(UUID.randomUUID());
        messageContent = new MessageContent("Message content");
        chatID = new ChatID(UUID.randomUUID());
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