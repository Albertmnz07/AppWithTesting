package domain.repositories;

import domain.entities.User;
import domain.valueObject.UserId;

import java.util.Optional;

public interface UserRepositorie {
    void save(User user);
    Optional<User> findById(UserId userId);
}
