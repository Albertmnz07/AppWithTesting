package domain.valueObject;

import main.domain.error.ErrorCode;
import main.domain.exceptions.PasswordEmptyException;
import main.domain.exceptions.PasswordTooLongException;
import main.domain.exceptions.PasswordTooShortException;
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
    void shouldThrowsPasswordTooShortException(){
        String password = "a".repeat(Password.MIN_LENGTH - 1);

        PasswordTooShortException error = assertThrows(PasswordTooShortException.class,
                () -> new Password(password));

		assertEquals(ErrorCode.PASSWORD_TOO_SHORT , error.getCode());

    }

	@Test
	void shouldThrowPasswordEmptyException(){
		PasswordEmptyException error = assertThrows(PasswordEmptyException.class,
				() -> new Password(""));

		assertEquals(ErrorCode.PASSWORD_EMPTY , error.getCode());

	}

	@Test
	void shouldThrownPasswordTooLongException(){
		PasswordTooLongException error = assertThrows(PasswordTooLongException.class,
				() -> new Password("a".repeat(Password.MAX_LENGTH + 1)));

		assertEquals(ErrorCode.PASSWORD_TOO_LONG , error.getCode());
	}

}
