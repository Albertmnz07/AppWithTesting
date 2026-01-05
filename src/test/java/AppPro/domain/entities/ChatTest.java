package AppPro.domain.entities;

import AppPro.domain.entities.Chat;
import AppPro.domain.entities.User;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.user.SameUsersException;
import AppPro.domain.valueObject.ChatId;
import AppPro.domain.valueObject.Password;
import AppPro.domain.valueObject.UserId;
import AppPro.domain.valueObject.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    private User userA , userB;
    private ChatId id;

    @BeforeEach
    void setUp(){
        userA = new User(new UserName(TestConstants.USER_NAME) , new Password(TestConstants.PASSWORD) , UserId.generate());
        userB = new User(new UserName(TestConstants.DF_USER_NAME) , new Password(TestConstants.DF_PASSWORD) , UserId.generate());
    }

    @Test
    void shouldInstantiateChat(){
        Chat chat = new Chat(userA.getUserId() , userB.getUserId() , new ChatId(UUID.randomUUID()));
        assertNotNull(chat);
    }

    @Test
    void shouldGetUserA(){
        Chat chat = new Chat(userA.getUserId() , userB.getUserId() , new ChatId(UUID.randomUUID()));
        assertEquals(userA.getUserId() , chat.getUserA());
    }

    @Test
    void shouldGetUserB(){
        Chat chat = new Chat(userA.getUserId() , userB.getUserId() , new ChatId(UUID.randomUUID()));
        assertEquals(userB.getUserId() , chat.getUserB());
    }

    @Test
    void shouldGetChatId(){
        Chat chat = new Chat(userA.getUserId() , userB.getUserId() , id);
        assertEquals(id , chat.getChatId());
    }

    @Test
    void shouldBeBothDifferentUsers(){
        assertNotEquals(userA, userB);
    }

    @Test
    void shouldThrowSameUsersException(){
        SameUsersException error = assertThrows(SameUsersException.class ,
                () -> new Chat(userA.getUserId() , userA.getUserId() , id));

        assertEquals(ErrorCode.SAME_USERS_IN_CHAT, error.getCode());


    }

}