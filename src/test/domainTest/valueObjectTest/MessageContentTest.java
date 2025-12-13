package domainTest.valueObjectTest;

import main.domain.exceptions.MessageContentInvalidException;
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
        MessageContentInvalidException error = assertThrows(MessageContentInvalidException.class
        , () -> new MessageContent("a".repeat(MessageContent.MAX_LENGTH + 1)));

        assertEquals(MessageContent.TOO_LONG_ERROR , error.getMessage());


    }
    @Test
    void shouldNThrowNotEmptyException(){
        MessageContentInvalidException error = assertThrows(MessageContentInvalidException.class
                , () -> new MessageContent(""));

        assertEquals(MessageContent.NOT_EMPTY_ERROR , error.getMessage());
    }

    @Test
    void shouldGetValue(){
        String message = "Message";
        MessageContent messageContent = new MessageContent(message);

        assertEquals(message , messageContent.getValue());
    }

}