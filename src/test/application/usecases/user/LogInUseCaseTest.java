package application.usecases.user;

import main.java.domain.error.ErrorCode;
import main.java.infrastructure.persistence.inmemory.FakeUserRepository;
import main.java.application.usecases.user.CreateUserUseCase;
import main.java.application.usecases.user.LogInUseCase;
import main.java.domain.entities.User;
import main.java.domain.exceptions.password.PasswordMismatchException;
import main.java.domain.exceptions.user.UserNotFoundException;
import org.junit.jupiter.api.Test;
import main.java.temporalUtils.TestConstants;
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

            assertEquals(ErrorCode.USER_NOT_FOUND , error.getCode());

        }


}