package AppPro.domain.valueObject;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.message.MessageEmptyException;
import AppPro.domain.exceptions.message.MessageTooLongException;
import AppPro.domain.valueObject.MessageContent;
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

        assertEquals(ErrorCode.MESSAGE_TOO_LONG , error.getCode());
    }

    @Test
    void shouldNThrowNotEmptyException(){
        MessageEmptyException error = assertThrows(MessageEmptyException.class
                , () -> new MessageContent(""));

        assertEquals(ErrorCode.MESSAGE_EMPTY , error.getCode());

    }

    @Test
    void shouldGetValue(){
        String message = "Message";
        MessageContent messageContent = new MessageContent(message);

        assertEquals(message , messageContent.getValue());
    }

}