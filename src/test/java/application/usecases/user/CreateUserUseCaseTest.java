package application.usecases.user;

import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.user.UserNameAlreadyExistsException;
import AppPro.infrastructure.persistence.inmemory.FakeUserRepository;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

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