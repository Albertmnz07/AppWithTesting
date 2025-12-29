package main.java.application.usecases.user;

import main.java.domain.entities.User;
import main.java.domain.exceptions.user.UserNameAlreadyExistsException;
import main.java.domain.repositories.UserRepository;
import main.java.domain.valueObject.Password;
import main.java.domain.valueObject.UserId;
import main.java.domain.valueObject.UserName;

public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(String userNameString , String passwordString){

        UserName userName = new UserName(userNameString);
        Password password = new Password(passwordString);
        UserId userId = UserId.generate();

        User user = new User(userName , password , userId);

        if (userRepository.findByUserName(user.getUserName().getValue()).isPresent()){
            throw new UserNameAlreadyExistsException();
        }

        userRepository.save(user);

        return user;

    }
}
