package applicationTest.usecasesTest.userTest;

import domainTest.repositoriesTest.FakeUserRepository;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.LogInUseCase;
import main.domain.entities.User;
import main.domain.exceptions.InvalidCredentialsException;
import main.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import utilsTest.TestConstants;
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
        void shouldThrowInvalidCredentialException(){

            FakeUserRepository userRepository = new FakeUserRepository();

            CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
            LogInUseCase logInUseCase = new LogInUseCase(userRepository);

            User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

            InvalidCredentialsException error = assertThrows(InvalidCredentialsException.class ,
                    () -> logInUseCase.execute(TestConstants.USER_NAME , TestConstants.DF_PASSWORD));

            assertEquals(InvalidCredentialsException.MESSAGE , error.getMessage());



        }

        @Test
        void shouldThrowUserNotFoundException(){
            FakeUserRepository userRepository = new FakeUserRepository();

            LogInUseCase logInUseCase = new LogInUseCase(userRepository);

            UserNotFoundException error = assertThrows(UserNotFoundException.class , () -> logInUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD));

            assertEquals(error.getMessage() , UserNotFoundException.MESSAGE);

        }


}