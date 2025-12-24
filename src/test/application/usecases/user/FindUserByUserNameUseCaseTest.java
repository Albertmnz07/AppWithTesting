package application.usecases.user;

import main.domain.error.ErrorCode;
import main.domain.exceptions.user.UserSearchHimSelfException;
import main.domain.valueObject.UserId;
import main.infrastructure.persistence.inmemory.FakeUserRepository;
import main.domain.exceptions.user.UserNotFoundException;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.FindUserByUserNameUseCase;
import main.domain.entities.User;
import main.domain.repositories.UserRepository;
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

        User foundUser = findUserByUserNameUseCase.execute(UserId.generate() , user.getUserName().getValue());

        assertEquals(user , foundUser);
    }

    @Test
    void shouldThrowUserNotFoundException(){
        User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        UserNotFoundException error = assertThrows(UserNotFoundException.class,
                () -> findUserByUserNameUseCase.execute(UserId.generate() , TestConstants.DF_USER_NAME));

        assertEquals(ErrorCode.USER_NOT_FOUND , error.getCode());
    }

    @Test
    void shouldThrowUserSearchHimselfException(){
        User user = createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD);

        UserSearchHimSelfException error = assertThrows(UserSearchHimSelfException.class ,
                () -> findUserByUserNameUseCase.execute(user.getUserId() , TestConstants.USER_NAME));

        assertEquals(ErrorCode.USER_SEARCH_HIMSELF , error.getCode());
    }

}