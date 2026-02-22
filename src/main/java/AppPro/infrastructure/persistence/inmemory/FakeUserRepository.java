package AppPro.infrastructure.persistence.inmemory;

import AppPro.domain.entities.Chat;
import AppPro.domain.entities.User;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public List<User> findAllByUserName(String userNamePart) {
        return storage.values().stream()
                .filter(user -> user.getUserName().getValue().startsWith(userNamePart))
                .sorted(Comparator.comparingInt(user -> user.getUserName().getValue().length()))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
