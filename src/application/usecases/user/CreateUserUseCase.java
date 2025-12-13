package application.usecases.user;

import domain.entities.User;
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
        UserId userId = new UserId();

        User user = new User(userName , password , userId);

        userRepository.save(user);

        return user;

    }
}
