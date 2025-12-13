package application.usecases.user;

import domain.entities.User;
import domain.repositories.UserRepository;
import domain.valueObject.UserId;
import domain.valueObject.UserName;

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
}
