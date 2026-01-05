package AppPro.domain.repositories;

import AppPro.domain.entities.User;
import AppPro.domain.valueObject.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByUserName(String userName);
}
