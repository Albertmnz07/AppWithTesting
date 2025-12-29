package main.java.application.usecases.user;

import main.java.domain.entities.User;
import main.java.domain.exceptions.password.PasswordMismatchException;
import main.java.domain.exceptions.user.UserNotFoundException;
import main.java.domain.repositories.UserRepository;
import main.java.domain.valueObject.Password;

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
