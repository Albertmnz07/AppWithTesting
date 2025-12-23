package domain.valueObject;

import main.domain.exceptions.MessageEmptyException;
import main.domain.exceptions.MessageTooLongException;
import main.domain.valueObject.MessageContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageContentTest {

    @Test
    void shouldInstantiateMessageContent(){
        MessageContent messageContent = new MessageContent("Message");
        assertNotNull(messageContent);
    }

    @Test
    void shouldThrowTooLongException(){
        MessageTooLongException error = assertThrows(MessageTooLongException.class
        , () -> new MessageContent("a".repeat(MessageContent.MAX_LENGTH + 1)));



    }
    @Test
    void shouldNThrowNotEmptyException(){
        MessageEmptyException error = assertThrows(MessageEmptyException.class
                , () -> new MessageContent(""));

    }

    @Test
    void shouldGetValue(){
        String message = "Message";
        MessageContent messageContent = new MessageContent(message);

        assertEquals(message , messageContent.getValue());
    }

}