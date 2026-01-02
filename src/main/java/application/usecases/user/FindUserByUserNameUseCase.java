package application.usecases.user;

import domain.entities.User;
import domain.exceptions.user.UserNotFoundException;
import domain.exceptions.user.UserSearchHimSelfException;
import domain.repositories.UserRepository;
import domain.valueObject.UserId;
import org.springframework.stereotype.Service;

@Service
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
