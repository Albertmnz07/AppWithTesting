package domain.valueObject;

import main.domain.error.ErrorCode;
import main.domain.exceptions.user.UserNameEmptyException;
import main.domain.exceptions.user.UserNameTooLongException;
import main.domain.exceptions.user.UserNameTooShortException;
import main.domain.valueObject.UserName;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserNameTest {

	@Test
	void shouldCreateObject() {
		@SuppressWarnings("unused")
		UserName name = new UserName(TestConstants.USER_NAME);
	}
	
	@Test
	void shouldGetValue() {
		UserName name = new UserName(TestConstants.USER_NAME);
		
		assertEquals(TestConstants.USER_NAME , name.getValue());
	}
	
	@Test
	void shouldThrowUsernameTooShortException() {
		String value = "a".repeat(UserName.MIN_LENGTH - 1);
		
		UserNameTooShortException error = assertThrows(UserNameTooShortException.class ,
				() -> new UserName(value));
		
		assertEquals(ErrorCode.USERNAME_TOO_SHORT , error.getCode());
	
	}
	
	@Test
	void shouldThrowUsernameTooLongExceptions() {
		String value = "a".repeat(UserName.MAX_LENGTH + 1);
		
		UserNameTooLongException error = assertThrows(UserNameTooLongException.class ,
				() -> new UserName(value));
		
		assertEquals(ErrorCode.USERNAME_TOO_LONG , error.getCode());
	}

	@Test
	void shouldThrowUsernameEmptyException(){
		UserNameEmptyException error = assertThrows(UserNameEmptyException.class , () -> new UserName(""));

		assertEquals(ErrorCode.USERNAME_EMPTY , error.getCode());
	}

	@Test
	void shouldReturnTrueIfEquals(){
		UserName userName = new UserName(TestConstants.USER_NAME);
		UserName sameUserName = new UserName(TestConstants.USER_NAME);
	}

}
