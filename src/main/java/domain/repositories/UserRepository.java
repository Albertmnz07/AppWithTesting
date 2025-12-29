package main.java.domain.repositories;

import main.java.domain.entities.User;
import main.java.domain.valueObject.UserId;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByUserName(String userName);
}
