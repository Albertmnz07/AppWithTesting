package domainTest.valueObjectTest;

import main.domain.exceptions.UserNameInvalidException;
import main.domain.valueObject.UserName;
import org.junit.jupiter.api.Test;
import utilsTest.TestConstants;

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
