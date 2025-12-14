package main.application.usecases.user;

import main.domain.entities.User;
import main.application.exceptions.InvalidCredentialsException;
import main.application.exceptions.UserNotFoundException;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.Password;
import main.domain.valueObject.UserName;

public class LogInUseCase {

    UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userNameStr , String passwordStr){

        UserName userName = new UserName(userNameStr);
        Password password = new Password(passwordStr);

        User user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);

        if (!user.passwordMatches(password)){
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
