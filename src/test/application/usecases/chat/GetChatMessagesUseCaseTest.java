package application.usecases.chat;

import main.domain.error.ErrorCode;
import main.infrastructure.persistence.inmemory.FakeChatRepository;
import main.infrastructure.persistence.inmemory.FakeMessageRepository;
import main.domain.exceptions.chat.ChatNotFoundException;
import main.application.usecases.message.GetChatMessagesUseCase;
import main.domain.entities.Chat;
import main.domain.entities.Message;
import main.domain.repositories.ChatRepository;
import main.domain.repositories.MessageRepository;
import main.domain.valueObject.ChatId;
import main.domain.valueObject.MessageContent;
import main.domain.valueObject.MessageId;
import main.domain.valueObject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.temporalUtils.TestConstants;

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