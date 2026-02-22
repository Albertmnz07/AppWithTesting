package AppPro.domain.service;

import AppPro.domain.entities.User;
import AppPro.domain.exceptions.user.UserNotFoundException;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserFinder {
    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User find(UserId userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll(String query){
        return userRepository.findAllByUserName(query);
    }
}
