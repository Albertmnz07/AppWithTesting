package main.application.usecases.user;

import main.domain.exceptions.user.UserNotFoundException;
import main.domain.entities.User;
import main.domain.repositories.UserRepository;

public class FindUserByUserNameUseCase {
    UserRepository userRepository;

    public FindUserByUserNameUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userName){

        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);


        return user;
    }
}
