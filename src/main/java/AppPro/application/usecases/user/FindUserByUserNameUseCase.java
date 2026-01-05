package AppPro.application.usecases.user;

import AppPro.domain.entities.User;
import AppPro.domain.exceptions.user.UserNotFoundException;
import AppPro.domain.exceptions.user.UserSearchHimSelfException;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.UserId;
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
