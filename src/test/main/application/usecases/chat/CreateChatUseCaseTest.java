package main.application.usecases.chat;

import domainTest.repositoriesTest.FakeChatRepository;
import domainTest.repositoriesTest.FakeUserRepository;
import main.domain.entities.Chat;
import main.domain.valueObject.UserId;
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

}