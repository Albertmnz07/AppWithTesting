package application.usecases.chat;

import domain.entities.Chat;
import domain.error.ErrorCode;
import domain.exceptions.chat.ChatAlreadyExistsException;
import domain.valueObject.UserId;
import infrastructure.persistence.inmemory.FakeChatRepository;
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