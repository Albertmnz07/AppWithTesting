package test.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.exceptions.UserNameInvalidException;
import domain.valueObject.UserName;
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
