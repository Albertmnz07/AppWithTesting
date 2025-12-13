package test.domain.exceptions;

import main.domain.exceptions.UserNameInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserNameInvalidExceptionTest {

	@Test
	void shouldInstantiateUserNameInvalidException() {
		@SuppressWarnings("unused")
		UserNameInvalidException error = new UserNameInvalidException("");
	}
	
	@Test 
	void shouldCatchUserNameInvalidException(){
		UserNameInvalidException error = new UserNameInvalidException("error");
		assertEquals("error" , error.getMessage());
	}

}
