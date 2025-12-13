package domainTest.valueObjectTest;

import main.domain.valueObject.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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