package application.usecases.user;

import domain.error.ErrorCode;
import domain.exceptions.user.UserSearchHimSelfException;
import domain.valueObject.UserId;
import infrastructure.persistence.inmemory.FakeUserRepository;
import domain.exceptions.user.UserNotFoundException;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import domain.entities.User;
import domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import temporalUtils.TestConstants;

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