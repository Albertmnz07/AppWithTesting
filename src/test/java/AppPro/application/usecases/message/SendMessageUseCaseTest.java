package AppPro.application.usecases.message;

import AppPro.application.usecases.chat.CreateChatUseCase;
import AppPro.application.usecases.message.SendMessageUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.chat.ChatNotFoundException;
import AppPro.domain.exceptions.user.UserNotParticipantInChat;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.UserId;
import AppPro.infrastructure.persistence.inmemory.FakeChatRepository;
import AppPro.infrastructure.persistence.inmemory.FakeMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        assertEquals(error.getCode() , ErrorCode.CHAT_NOT_FOUND);
    }

    @Test
    void shouldThrowUserNotParticipantException(){
        UserId userA = UserId.generate();
        UserId userB = UserId.generate();

        Chat chat = createChatUseCase.execute(userA , userB);

        UserNotParticipantInChat error = assertThrows(UserNotParticipantInChat.class ,
                () -> sendMessageUseCase.execute(chat.getChatId() , UserId.generate() , TestConstants.MESSAGE));

        assertEquals(ErrorCode.USER_NOT_PARTICIPANT , error.getCode());


    }

}