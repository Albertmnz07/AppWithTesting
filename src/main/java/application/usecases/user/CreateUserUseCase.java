package application.usecases.user;

import domain.entities.User;
import domain.exceptions.user.UserNameAlreadyExistsException;
import domain.repositories.UserRepository;
import domain.valueObject.Password;
import domain.valueObject.UserId;
import domain.valueObject.UserName;

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
