package application.usecases.user;

import main.java.domain.error.ErrorCode;
import main.java.domain.exceptions.user.UserSearchHimSelfException;
import main.java.domain.valueObject.UserId;
import main.java.infrastructure.persistence.inmemory.FakeUserRepository;
import main.java.domain.exceptions.user.UserNotFoundException;
import main.java.application.usecases.user.CreateUserUseCase;
import main.java.application.usecases.user.FindUserByUserNameUseCase;
import main.java.domain.entities.User;
import main.java.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.temporalUtils.TestConstants;

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