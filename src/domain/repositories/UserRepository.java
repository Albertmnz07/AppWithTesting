package domain.repositories;

import domain.entities.User;
import domain.valueObject.UserId;
import domain.valueObject.UserName;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByUserName(UserName userName);
}
