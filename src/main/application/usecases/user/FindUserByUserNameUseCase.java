package main.application.usecases.user;

import main.application.exceptions.UserNotFoundException;
import main.domain.entities.User;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.UserName;

public class FindUserByUserNameUseCase {
    UserRepository userRepository;

    public FindUserByUserNameUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(UserName userName){

        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);


        return user;
    }
}
