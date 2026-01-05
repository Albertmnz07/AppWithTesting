package AppPro.domain.valueObject;

import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.password.PasswordEmptyException;
import AppPro.domain.exceptions.password.PasswordTooLongException;
import AppPro.domain.exceptions.password.PasswordTooShortException;
import AppPro.temporalUtils.TestConstants;
import org.junit.jupiter.api.Test;

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
