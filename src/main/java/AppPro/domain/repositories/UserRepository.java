package AppPro.domain.repositories;

import AppPro.domain.entities.User;
import AppPro.domain.valueObject.UserId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(UserId userId);
    Optional<User> findByUserName(String userName);
    List<User> findAllByUserName(String userNamePart);
}
