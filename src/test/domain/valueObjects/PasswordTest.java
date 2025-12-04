package test.domain.valueObjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.valueObject.Password;

class PasswordTest {

	@Test
	void shouldCreatePasswordObject() {
		Password password = new Password("abcd");
	}
	
	@Test
	void shouldGetValue() {
		Password password = new Password("abc");
		
		assertEquals("abc" , password.getValue());
	}

}
