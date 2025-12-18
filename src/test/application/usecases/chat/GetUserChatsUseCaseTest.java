package application.usecases.chat;

import main.infrastructure.persistence.inmemory.FakeChatRepository;
import main.application.usecases.chat.CreateChatUseCase;
import main.application.usecases.chat.GetUserChatsUseCase;
import main.domain.entities.Chat;
import main.domain.valueObject.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetUserChatsUseCaseTest {

    FakeChatRepository chatRepository;
    UserId userId;
    GetUserChatsUseCase getUserChatsUseCase;
    CreateChatUseCase createChatUseCase;

    @BeforeEach
    void setUp(){
        chatRepository = new FakeChatRepository();
        getUserChatsUseCase = new GetUserChatsUseCase(chatRepository);
        createChatUseCase = new CreateChatUseCase(chatRepository);
        userId = UserId.generate();
        //user = new User(new UserName(TestConstants.USER_NAME) , new Password(TestConstants.PASSWORD) , UserId.generate());
    }

    @Test
    void shouldGetChatList(){

        Chat chat = createChatUseCase.execute(userId , UserId.generate());
        List<Chat> chatList = getUserChatsUseCase.execute(userId);

        assertNotNull(chatList);
        assertNotNull(chatList.get(0));
        assertEquals(chat , chatList.get(0));
        assertEquals(1, chatList.size());
        assertTrue(chatList.get(0).involves(userId));


    }

}