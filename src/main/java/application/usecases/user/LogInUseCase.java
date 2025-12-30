package application.usecases.user;

import domain.entities.User;
import domain.exceptions.password.PasswordMismatchException;
import domain.exceptions.user.UserNotFoundException;
import domain.repositories.UserRepository;

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
