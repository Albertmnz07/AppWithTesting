package main.application.usecases.user;

import main.domain.entities.User;
import main.domain.exceptions.PasswordMismatchException;
import main.application.exceptions.UserNotFoundException;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.Password;

public class LogInUseCase {

    UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userNameStr , String passwordStr){

        Password password = new Password(passwordStr);

        User user = userRepository.findByUserName(userNameStr).orElseThrow(UserNotFoundException::new);

        if (!user.passwordMatches(password)){
            throw new PasswordMismatchException();
        }

        return user;
    }
}
