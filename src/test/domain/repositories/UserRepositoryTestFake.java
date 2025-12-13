package domain.repositories;

import domain.entities.User;
import domain.valueObject.UserId;
import domain.valueObject.UserName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTestFake implements UserRepository{

    private final Map<UserId, User> storage = new HashMap<>();
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

}