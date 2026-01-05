package application.usecases.user;

import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.application.usecases.user.LogInUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.password.PasswordMismatchException;
import AppPro.domain.exceptions.user.UserNotFoundException;
import AppPro.infrastructure.persistence.inmemory.FakeUserRepository;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

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