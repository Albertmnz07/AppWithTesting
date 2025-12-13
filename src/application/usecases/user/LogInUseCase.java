package application.usecases.user;

import domain.entities.User;
import domain.exceptions.InvalidCredentialsException;
import domain.exceptions.UserNotFoundException;
import domain.repositories.UserRepository;
import domain.valueObject.Password;
import domain.valueObject.UserName;

public class LogInUseCase {

    UserRepository userRepository;

    public LogInUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userNameStr , String passwordStr){

        UserName userName = new UserName(userNameStr);
        Password password = new Password(passwordStr);

        User user = userRepository.findByUserName(userName).orElseThrow(() -> new UserNotFoundException());

        if (!user.passwordMatches(password)){
            throw new InvalidCredentialsException();
        }

        return user;
    }
}
