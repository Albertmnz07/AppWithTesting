package application.usecases.user;

import domain.repositories.FakeUserRepository;
import main.application.exceptions.UserNotFoundException;
import main.application.exceptions.UserSearchHimSelfException;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.FindUserByUserNameUseCase;
import main.domain.entities.User;
import main.domain.repositories.UserRepository;
import main.domain.valueObject.UserName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.TestConstants;

import static org.junit.jupiter.api.Assertions.*;

class FindUserByUserNameUseCaseTest {

    UserRepository userRepository;
    FindUserByUserNameUseCase findUserByUserNameUseCase;
    CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setUp(){
        userRepository = new FakeUserRepository();
        createUserUseCase = new CreateUserUseCase(userRepository);
        findUserByUserNameUseCase = new FindUserByUserNameUseCase(userRepository);
    }

    @Test
    void shouldGetUser(){
        User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        User foundUser = findUserByUserNameUseCase.execute(user.getUserName());

        assertEquals(user , foundUser);
    }

    @Test
    void shouldThrowUserNotFoundException(){
        User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        UserNotFoundException error = assertThrows(UserNotFoundException.class,
                () -> findUserByUserNameUseCase.execute(new UserName(TestConstants.DF_USER_NAME)));

        assertEquals(error.getMessage() , UserNotFoundException.MESSAGE);
    }

}