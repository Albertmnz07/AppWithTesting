package domain.valueObject;

import main.domain.exceptions.PasswordInvalidException;
import main.domain.valueObject.Password;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

	@Test
	void shouldCreatePasswordObject() {
		Password password = new Password(TestConstants.PASSWORD);
		assertNotNull(password);
	}
	
	@Test
	void shouldGetValue() {
		Password password = new Password(TestConstants.PASSWORD);
		
		assertEquals(TestConstants.PASSWORD , password.getValue());
	}

    @Test
    void shouldThrowsTooShortException(){
        String password = "a".repeat(Password.MIN_LENGTH - 1);

        PasswordInvalidException error = assertThrows(PasswordInvalidException.class,
                () -> new Password(password));

        assertEquals(Password.ERROR_TOO_SHORT , error.getMessage());

    }

}
