package application.usecases.chat;

import AppPro.application.usecases.message.GetChatMessagesUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.chat.ChatNotFoundException;
import AppPro.domain.repositories.ChatRepository;
import AppPro.domain.repositories.MessageRepository;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.MessageContent;
import AppPro.domain.valueObject.MessageId;
import AppPro.domain.valueObject.UserId;
import AppPro.infrastructure.persistence.inmemory.FakeChatRepository;
import AppPro.infrastructure.persistence.inmemory.FakeMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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