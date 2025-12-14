package main.application.usecases.user;

import main.domain.entities.User;
import main.application.exceptions.UserNameAlreadyExistsException;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.Password;
import main.domain.valueObject.UserId;
import main.domain.valueObject.UserName;

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

        if (userRepository.findByUserName(user.getUserName()).isPresent()){
            throw new UserNameAlreadyExistsException();
        }

        userRepository.save(user);

        return user;

    }
}
