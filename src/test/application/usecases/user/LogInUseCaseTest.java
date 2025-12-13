package application.usecases.user;

import domain.entities.User;
import domain.repositories.UserRepository;
import domain.repositories.UserRepositoryTestFake;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import static org.junit.jupiter.api.Assertions.*;

class LogInUseCaseTest {

    @Test
    void shouldThrowInvalidCredentialException(){

        UserRepository userRepository = new UserRepositoryTestFake();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        LogInUseCase logInUseCase = new LogInUseCase(userRepository);

        User user = createUserUseCase.execute( TestConstants.USER_NAME , TestConstants.PASSWORD);
        User loggedUser = logInUseCase.execute( TestConstants.USER_NAME , TestConstants.PASSWORD);

        assertEquals(user.getUserId() , loggedUser.getUserId());



    }

}