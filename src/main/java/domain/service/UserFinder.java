package domain.service;

import domain.entities.User;
import domain.exceptions.user.UserNotFoundException;
import domain.repositories.UserRepository;
import domain.valueObject.UserId;
import org.springframework.stereotype.Service;

@Service
public class UserFinder {
    private final UserRepository userRepository;

    public UserFinder(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User find(UserId userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
