package domain.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDEmptyExceptionTest {

    @Test
    void shouldInstantiateIDEmptyException(){
        IDEmptyException error = new IDEmptyException("");
    }

    @Test
    void shouldCatchIDEmptyException(){
        IDEmptyException error = new IDEmptyException("abc");
        assertEquals("abc" , error.getMessage());
    }

}