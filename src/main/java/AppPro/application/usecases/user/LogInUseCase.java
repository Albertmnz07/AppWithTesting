package AppPro.application.usecases.user;

import AppPro.domain.entities.User;
import AppPro.domain.exceptions.password.PasswordMismatchException;
import AppPro.domain.exceptions.user.UserNotFoundException;
import AppPro.domain.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LogInUseCase {

    UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userNameStr , String passwordStr){

        //Password password = new Password(passwordStr);

        User user = userRepository.findByUserName(userNameStr).orElseThrow(UserNotFoundException::new);

        if (!(user.passwordMatches(passwordStr))){
            throw new PasswordMismatchException();
        }

        return user;
    }
}
