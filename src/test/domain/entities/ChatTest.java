package domain.entities;

import domain.exceptions.SameUsersException;
import domain.valueObject.ChatID;
import domain.valueObject.Password;
import domain.valueObject.UserId;
import domain.valueObject.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    private User userA , userB;
    private ChatID id;

    @BeforeEach
    void setUp(){
        userA = new User(new UserName("Albert") ,
                new Password("abc") ,
                new UserId(UUID.randomUUID()));
        userB = new User(new UserName("Juan") ,
                new Password("cba") ,
                new UserId(UUID.randomUUID()));
        id = new ChatID(UUID.randomUUID());
    }

    @Test
    void shouldInstantiateChat(){
        Chat chat = new Chat(userA , userB , new ChatID(UUID.randomUUID()));
        assertNotNull(chat);
    }

    @Test
    void shouldGetUserA(){
        Chat chat = new Chat(userA , userB , new ChatID(UUID.randomUUID()));
        assertEquals(userA , chat.getUserA());
    }

    @Test
    void shouldGetUserB(){
        Chat chat = new Chat(userA , userB , new ChatID(UUID.randomUUID()));
        assertEquals(userB , chat.getUserB());
    }

    @Test
    void shouldGetChatId(){
        Chat chat = new Chat(userA , userB , id);
        assertEquals(id , chat.getChatID());
    }

    @Test
    void shouldBeBothDifferentUsers(){
        assertNotEquals(userA, userB);
    }

    @Test
    void shouldThrowSameUsersException(){
        SameUsersException error = assertThrows(SameUsersException.class ,
                () -> new Chat(userA , userA , id));

        assertEquals(Chat.SAME_USER_ERROR , error.getMessage());


    }

}