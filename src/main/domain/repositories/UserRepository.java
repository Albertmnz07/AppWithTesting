package main.domain.repositories;

import main.domain.entities.User;
import main.domain.valueObject.UserId;
import main.domain.valueObject.UserName;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByUserName(UserName userName);
}
