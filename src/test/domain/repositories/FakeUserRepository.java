package domain.repositories;

import main.domain.entities.User;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.UserId;
import main.domain.valueObject.UserName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeUserRepository implements UserRepository {
    private final Map<UserId , User> storage = new HashMap<>();
    @Override
    public void save(User user) {
        storage.put(user.getUserId() , user);
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return Optional.ofNullable(storage.get(userId));
    }

    @Override
    public Optional<User> findByUserName(UserName userName) {
        return storage.values().stream().filter(user -> user.getUserName().equals(userName)).findFirst();
    }

    @Test
    void none(){

    }
}
