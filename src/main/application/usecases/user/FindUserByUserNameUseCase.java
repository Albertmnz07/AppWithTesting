package main.application.usecases.user;

import main.domain.exceptions.user.UserNotFoundException;
import main.domain.entities.User;
import main.domain.exceptions.user.UserSearchHimSelfException;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.UserId;

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
