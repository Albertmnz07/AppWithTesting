package domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordInvalidExceptionTest {

    @Test
    void shouldInstantiatePasswordInvalidException(){
        UserNameInvalidException error = new UserNameInvalidException("");
    }

    @Test
    void shouldCatchPasswordInvalidException(){
        UserNameInvalidException error = new UserNameInvalidException("abc");
        assertEquals("abc" , error.getMessage());
    }

}