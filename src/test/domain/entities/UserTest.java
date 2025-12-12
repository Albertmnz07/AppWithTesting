package test.domain.entities;

import static org.junit.jupiter.api.Assertions.*;

import domain.entities.User;
import domain.valueObject.Password;
import domain.valueObject.UserId;
import domain.valueObject.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

class UserTest {

    public static final UserId userId = new UserId(UUID.randomUUID());
    public static final UserName userName = new UserName("Albert");
    public static final Password password = new Password("abc");

    @BeforeEach
    void setUp(){
        UserId userId = new UserId(UUID.randomUUID());
        UserName userName = new UserName("Albert");
        Password password = new Password("abc");
    }

	@Test
	void shouldInstantiateUser() {
		User user = new User(userName , password , userId);
        assertNotNull(user);
	}

    @Test
    void shouldGetUserName(){
        User user = new User(userName , password , userId);
        assertEquals(userName , user.getUserName());
    }

    @Test
    void shouldGetPassword(){
        User user = new User(userName , password , userId);
        assertEquals(password , user.getPassword());
    }

    @Test
    void shouldGetUserId(){
        User user = new User(userName , password , userId);
        assertEquals(userId, user.getUserId());
    }

    @Test
    void shouldChangeUserName(){
        UserName newUserName = new UserName("Alber2");
        User user = new User(userName , password , userId);
        user.changeUserName(newUserName);
        assertEquals(newUserName , user.getUserName());
    }

    @Test
    void shouldChangePassword(){
        Password newPassword = new Password("cba");
        User user = new User(userName , password , userId);
        user.changePassword(newPassword);
        assertEquals(newPassword , user.getPassword());

    }

    @Test
    void shouldCheckIfEqualsFalse(){
        UUID differentId = UUID.randomUUID();
        User user = new User(userName , password , userId);
        User differentUser = new User(userName , password , new UserId(differentId));

        assertFalse(user.equals(differentUser));
    }

    @Test
    void shouldCheckIfEqualsTrue(){
        UUID differentId = UUID.randomUUID();
        User user = new User(userName , password , userId);

        assertTrue(user.equals(user));
    }

}
