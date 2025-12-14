package domain.exceptions;

import main.domain.exceptions.IdEmptyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdEmptyExceptionTest {

    @Test
    void shouldInstantiateIDEmptyException(){
        IdEmptyException error = new IdEmptyException("");
    }

    @Test
    void shouldCatchIDEmptyException(){
        IdEmptyException error = new IdEmptyException("abc");
        assertEquals("abc" , error.getMessage());
    }

}