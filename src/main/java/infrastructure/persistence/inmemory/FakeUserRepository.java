package infrastructure.persistence.inmemory;

import domain.entities.User;
import domain.repositories.UserRepository;
import domain.valueObject.UserId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
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

}
