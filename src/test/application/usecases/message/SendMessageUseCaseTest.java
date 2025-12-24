package application.usecases.message;

import main.infrastructure.persistence.inmemory.FakeChatRepository;
import main.infrastructure.persistence.inmemory.FakeMessageRepository;
import main.domain.exceptions.chat.ChatNotFoundException;
import main.domain.exceptions.user.UserNotParticipantInChat;
import main.application.usecases.chat.CreateChatUseCase;
import main.application.usecases.message.SendMessageUseCase;
import main.domain.entities.Chat;
import main.domain.entities.Message;
import main.domain.repositories.ChatRepository;
import main.domain.repositories.MessageRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageUseCaseTest {

    MessageRepository messageRepository;
    SendMessageUseCase sendMessageUseCase;
    ChatRepository chatRepository;
    CreateChatUseCase createChatUseCase;

    @BeforeEach
    void setUp(){
        messageRepository = new FakeMessageRepository();
        chatRepository = new FakeChatRepository();
        createChatUseCase = new CreateChatUseCase(chatRepository);
        sendMessageUseCase = new SendMessageUseCase(messageRepository , chatRepository);

    }

    @Test
    void shouldSendMessage(){
        UserId userA = UserId.generate();
        UserId userB = UserId.generate();
        ChatId chatId = ChatId.generate();

        Chat chat = new Chat(userA , userB , chatId);
        chatRepository.save(chat);

        Message message = sendMessageUseCase.execute(chat.getChatId() , userA , TestConstants.MESSAGE);

        List<Message> messageList = messageRepository.findByChatId(chat.getChatId());

        assertEquals(1 , messageList.size());
        assertEquals(message , messageList.get(0));
        assertEquals(message.getMessageContent() , messageList.get(0).getMessageContent());


    }

    @Test
    void shouldThrowChatNotFoundException(){
        UserId userA = UserId.generate();
        UserId userB = UserId.generate();

        Chat chat = createChatUseCase.execute(userA , userB);

        ChatNotFoundException error = assertThrows(ChatNotFoundException.class ,
                () -> sendMessageUseCase.execute(ChatId.generate() , userA , TestConstants.MESSAGE));

        assertEquals(error.getMessage() , ChatNotFoundException.MESSAGE);
    }

    @Test
    void shouldThrowUserNotParticipantException(){
        UserId userA = UserId.generate();
        UserId userB = UserId.generate();

        Chat chat = createChatUseCase.execute(userA , userB);

        UserNotParticipantInChat error = assertThrows(UserNotParticipantInChat.class ,
                () -> sendMessageUseCase.execute(chat.getChatId() , UserId.generate() , TestConstants.MESSAGE));

        assertEquals(error.getMessage() , UserNotParticipantInChat.MESSAGE);


    }

}