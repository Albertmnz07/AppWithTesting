package application.usecases.user;

import domain.entities.User;
import domain.exceptions.user.UserNotFoundException;
import domain.repositories.UserRepository;
import domain.valueObject.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserById {

    public final UserRepository userRepository;

    public FindUserById(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(UserId requestId , UserId userId){

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return user;

    }
}
