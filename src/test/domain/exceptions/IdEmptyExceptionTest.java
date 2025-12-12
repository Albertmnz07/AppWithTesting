package domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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