package main.java.infrastructure.persistence.inmemory;

import main.java.domain.entities.User;
import main.java.domain.repositories.UserRepository;
import main.java.domain.valueObject.UserId;
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
    public Optional<User> findByUserName(String userName) {
        return storage.values().stream().filter(user -> user.getUserName().getValue().equals(userName)).findFirst();
    }

    @Test
    void none(){

    }
}
