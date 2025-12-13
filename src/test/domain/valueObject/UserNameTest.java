package domain.valueObject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.exceptions.UserNameInvalidException;
import utils.TestConstants;

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
	void shouldTooShortThrowsException() {
		String value = "a".repeat(UserName.MIN_LENGTH - 1);
		
		UserNameInvalidException error = assertThrows(UserNameInvalidException.class , 
				() -> new UserName(value));
		
		assertEquals(UserName.ERROR_TOO_SHORT , error.getMessage());
	
	}
	
	@Test
	void shouldTooLongThrowsExceptions() {
		String value = "a".repeat(UserName.MAX_LENGTH + 1);
		
		UserNameInvalidException error = assertThrows(UserNameInvalidException.class ,
				() -> new UserName(value));
		
		assertEquals(UserName.ERROR_TOO_LONG , error.getMessage());
	}

	@Test
	void shouldReturnTrueIfEquals(){
		UserName userName = new UserName(TestConstants.USER_NAME);
		UserName sameUserName = new UserName(TestConstants.USER_NAME);
	}

}
