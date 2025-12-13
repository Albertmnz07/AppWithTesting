package domain.valueObject;

import domain.entities.User;
import domain.exceptions.IdEmptyException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

    @Test
    void shouldInstantiateUserId(){
        UserId id = new UserId();
        assertNotNull(id);

    }

    @Test
    void shouldGetValue(){
        UserId userId = new UserId();
        assertNotNull(userId);
    }

}