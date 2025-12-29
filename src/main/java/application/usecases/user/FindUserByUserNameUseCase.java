package main.java.application.usecases.user;

import main.java.domain.exceptions.user.UserNotFoundException;
import main.java.domain.entities.User;
import main.java.domain.exceptions.user.UserSearchHimSelfException;
import main.java.domain.repositories.UserRepository;
import main.java.domain.valueObject.UserId;

public class FindUserByUserNameUseCase {
    UserRepository userRepository;

    public FindUserByUserNameUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(UserId requestId , String userName){

        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);

        if (user.getUserId().equals(requestId)){
            throw new UserSearchHimSelfException();
        }


        return user;
    }
}
