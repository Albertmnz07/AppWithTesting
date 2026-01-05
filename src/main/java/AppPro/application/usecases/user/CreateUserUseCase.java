package AppPro.application.usecases.user;

import AppPro.domain.entities.User;
import AppPro.domain.exceptions.user.UserNameAlreadyExistsException;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.Password;
import AppPro.domain.valueObject.UserId;
import AppPro.domain.valueObject.UserName;
import org.springframework.stereotype.Service;

@Service
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
