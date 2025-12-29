package application.usecases.user;

import domain.error.ErrorCode;
import infrastructure.persistence.inmemory.FakeUserRepository;
import application.usecases.user.CreateUserUseCase;
import domain.entities.User;
import domain.exceptions.user.UserNameAlreadyExistsException;
import org.junit.jupiter.api.Test;
import temporalUtils.TestConstants;

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
    void shouldThrowUserNameAlreadyExistsException(){
        FakeUserRepository userRepository = new FakeUserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);

        User userA = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        UserNameAlreadyExistsException error = assertThrows(UserNameAlreadyExistsException.class ,
                () -> createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD));

        assertEquals(ErrorCode.USERNAME_ALREADY_EXISTS , error.getCode());
    }

}