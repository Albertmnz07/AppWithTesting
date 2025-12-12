package domain.valueObject;

import domain.exceptions.IdEmptyException;
import org.junit.jupiter.api.Test;

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
        IdEmptyException error = assertThrows(IdEmptyException.class , () -> new UserId(null));
        assertEquals(UserId.USERID_NULL_ERROR , error.getMessage());
    }

}