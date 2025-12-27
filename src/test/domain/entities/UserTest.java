package test.domain.entities;

import main.domain.entities.User;
import main.domain.valueObject.Password;
import main.domain.valueObject.UserId;
import main.domain.valueObject.UserName;
import org.junit.jupiter.api.Test;
import main.temporalUtils.TestConstants;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    public static final UserId userId = UserId.generate();
    public static final UserName userName = new UserName(TestConstants.USER_NAME);
    public static final Password password = new Password(TestConstants.PASSWORD);


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
        User user = new User(userName , password , userId);
        User differentUser = new User(userName , password , UserId.generate());

        assertFalse(user.equals(differentUser));
    }

    @Test
    void shouldCheckIfEqualsTrue(){
        User user = new User(userName , password , userId);

        assertTrue(user.equals(user));
    }

    @Test
    void shouldReturnTrueIfPasswordMatches(){
        Password samePassword = new Password(TestConstants.PASSWORD);
        assertTrue(password.equals(samePassword));

    }



}
