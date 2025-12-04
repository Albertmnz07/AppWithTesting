package test.domain.valueObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.exceptions.UserNameInvalidException;
import domain.valueObject.UserName;

class UserNameTest {

	@Test
	void shouldCreateObject() {
		@SuppressWarnings("unused")
		UserName name = new UserName("Alberto");
	}
	
	@Test
	void shouldGetValue() {
		UserName name = new UserName("Alberto");
		
		assertEquals("Alberto" , name.getValue());
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
	

}
