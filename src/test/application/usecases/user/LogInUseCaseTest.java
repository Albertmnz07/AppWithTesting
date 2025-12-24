package application.usecases.user;

import main.domain.error.ErrorCode;
import main.infrastructure.persistence.inmemory.FakeUserRepository;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.LogInUseCase;
import main.domain.entities.User;
import main.domain.exceptions.password.PasswordMismatchException;
import main.domain.exceptions.user.UserNotFoundException;
import org.junit.jupiter.api.Test;
import utils.TestConstants;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogInUseCaseTest {

    @Test
    void shouldLogIn(){
        FakeUserRepository userRepository = new FakeUserRepository();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        LogInUseCase logInUseCase = new LogInUseCase(userRepository);

        User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);
        User loggedUser = logInUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        assertEquals(user , loggedUser);


    }

        @Test
        void shouldThrowPasswordMismatchException(){

            FakeUserRepository userRepository = new FakeUserRepository();

            CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
            LogInUseCase logInUseCase = new LogInUseCase(userRepository);

            User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

            PasswordMismatchException error = assertThrows(PasswordMismatchException.class ,
                    () -> logInUseCase.execute(TestConstants.USER_NAME , TestConstants.DF_PASSWORD));

            assertEquals(ErrorCode.PASSWORD_MISMATCH, error.getCode());

        }

        @Test
        void shouldThrowUserNotFoundException(){
            FakeUserRepository userRepository = new FakeUserRepository();

            LogInUseCase logInUseCase = new LogInUseCase(userRepository);

            UserNotFoundException error = assertThrows(UserNotFoundException.class , () -> logInUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD));

            assertEquals(error.getMessage() , UserNotFoundException.MESSAGE);

        }


}