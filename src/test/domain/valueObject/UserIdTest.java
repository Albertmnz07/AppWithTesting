package domain.valueObject;

import domain.exceptions.IDEmptyException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    void shouldInstantiateUserId(){
        UserId id = new UserId(UUID.randomUUID());
        assertNotNull(id);

    }

    @Test
    void shouldGetValue(){
        UUID id = UUID.randomUUID();
        UserId userId = new UserId(id);
        assertEquals(userId.getValue() , id);
    }

    @Test
    void shouldCreateValidUUID(){
        UUID id = UUID.randomUUID();
        UserId userId = new UserId(id);

        assertEquals(userId.getValue() , id);
    }

    @Test
    void shouldThrowIfNull(){
        IDEmptyException error = assertThrows(IDEmptyException.class , () -> new UserId(null));
        assertEquals(UserId.USERID_NULL_ERROR , error.getMessage());
    }

}