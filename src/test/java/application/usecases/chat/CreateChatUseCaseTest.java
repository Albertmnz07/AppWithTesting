package application.usecases.chat;

import AppPro.application.usecases.chat.CreateChatUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.chat.ChatAlreadyExistsException;
import AppPro.domain.valueObject.UserId;
import AppPro.infrastructure.persistence.inmemory.FakeChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateChatUseCaseTest {

    FakeChatRepository chatRepository;
    CreateChatUseCase createChatUseCase;
    UserId userA;
    UserId userB;

    @BeforeEach
    void setUp(){
        chatRepository = new FakeChatRepository();
        createChatUseCase = new CreateChatUseCase(chatRepository);
        userA = UserId.generate();
        userB = UserId.generate();
    }

    @Test
    void shouldCreateChat(){

        Chat chat = createChatUseCase.execute(userA , userB);

        assertNotNull(chat);
        assertEquals(userA , chat.getUserA());
        assertEquals(userB , chat.getUserB());
        assertTrue(chatRepository.findById(chat.getChatId()).isPresent());

    }

    @Test
    void shouldThrowChatAlreadyExistsException(){
        Chat chat = createChatUseCase.execute(userA , userB);

        ChatAlreadyExistsException error = assertThrows(
                ChatAlreadyExistsException.class ,
                () -> createChatUseCase.execute(userA , userB)
        );

        assertEquals(ErrorCode.CHAT_ALREADY_EXISTS , error.getCode());
    }

}