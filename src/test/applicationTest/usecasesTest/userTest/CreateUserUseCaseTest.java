package applicationTest.usecasesTest.userTest;

import domainTest.repositoriesTest.FakeUserRepository;
import main.application.usecases.user.CreateUserUseCase;
import main.domain.entities.User;
import main.domain.exceptions.UserNameAlreadyExistsException;
import org.junit.jupiter.api.Test;
import utilsTest.TestConstants;

import static org.junit.jupiter.api.Assertions.*;


class CreateUserUseCaseTest {

    @Test
    void shouldCreateUser(){

        FakeUserRepository userRepository = new FakeUserRepository();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        String name = TestConstants.USER_NAME;
        String password = TestConstants.PASSWORD;
        User user = createUserUseCase.execute(name , password);

        assertEquals(name , user.getUserName().getValue());
        assertEquals(password , user.getPassword().getValue());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());

    }

    @Test
    void shouldThrowExceptionIfUserNameExists(){
        FakeUserRepository userRepository = new FakeUserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);

        User userA = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        UserNameAlreadyExistsException error = assertThrows(UserNameAlreadyExistsException.class ,
                () -> createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD));
    }

}