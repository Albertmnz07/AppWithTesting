package AppPro.domain.valueObject;

import AppPro.domain.valueObject.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserIdTest {

    @Test
    void shouldInstantiateUserId(){
        UserId id = UserId.generate();
        assertNotNull(id);

    }

    @Test
    void shouldGetValue(){
        UserId userId = UserId.generate();
        assertNotNull(userId);
    }

}