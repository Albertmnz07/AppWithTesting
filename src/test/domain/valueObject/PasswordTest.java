package domain.valueObject;

import static org.junit.jupiter.api.Assertions.*;

import domain.exceptions.PasswordInvalidException;
import org.junit.jupiter.api.Test;

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

    @Test
    void shouldThrowsTooShortException(){
        String password = "a".repeat(Password.MIN_LENGTH - 1);

        PasswordInvalidException error = assertThrows(PasswordInvalidException.class,
                () -> new Password(password));

        assertEquals(Password.ERROR_TOO_SHORT , error.getMessage());

    }

}
