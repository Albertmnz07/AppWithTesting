package application.usecases.chat;

import main.java.domain.error.ErrorCode;
import main.java.infrastructure.persistence.inmemory.FakeChatRepository;
import main.java.infrastructure.persistence.inmemory.FakeMessageRepository;
import main.java.domain.exceptions.chat.ChatNotFoundException;
import main.java.application.usecases.message.GetChatMessagesUseCase;
import main.java.domain.entities.Chat;
import main.java.domain.entities.Message;
import main.java.domain.repositories.ChatRepository;
import main.java.domain.repositories.MessageRepository;
import main.java.domain.valueObject.ChatId;
import main.java.domain.valueObject.MessageContent;
import main.java.domain.valueObject.MessageId;
import main.java.domain.valueObject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.temporalUtils.TestConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetChatMessagesUseCaseTest {

    MessageRepository messageRepository;
    ChatRepository chatRepository;
    GetChatMessagesUseCase getChatMessagesUseCase;

     @BeforeEach
    void setUp(){
         messageRepository = new FakeMessageRepository();
         chatRepository = new FakeChatRepository();
     }

     @Test
    void shouldGetMessageList(){
         ChatId chatId = ChatId.generate();
         UserId userId = UserId.generate();
         Message message = new Message(userId , MessageId.generate()  , new MessageContent(TestConstants.MESSAGE), chatId);
         Chat chat = new Chat(userId , UserId.generate() , chatId);

         chatRepository.save(chat);
         messageRepository.save(message);

         getChatMessagesUseCase = new GetChatMessagesUseCase(messageRepository , chatRepository);

         List<Message> messageList = getChatMessagesUseCase.execute(chatId);
         assertEquals(message , messageList.get(0));
         assertEquals(1, messageList.size());
         assertEquals(message.getSenderId() , messageList.get(0).getSenderId());
     }

     @Test
    void shouldThrowChatNotFoundException(){
         ChatId chatId = ChatId.generate();

         getChatMessagesUseCase = new GetChatMessagesUseCase(messageRepository , chatRepository);

         ChatNotFoundException error = assertThrows(ChatNotFoundException.class ,
                 () -> getChatMessagesUseCase.execute(chatId));

         assertEquals(ErrorCode.CHAT_NOT_FOUND , error.getCode());
     }

}