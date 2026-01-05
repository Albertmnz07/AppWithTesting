package application.usecases.user;

import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.application.usecases.user.FindUserByUserNameUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.error.ErrorCode;
import AppPro.domain.exceptions.user.UserNotFoundException;
import AppPro.domain.exceptions.user.UserSearchHimSelfException;
import AppPro.domain.repositories.UserRepository;
import AppPro.domain.valueObject.UserId;
import AppPro.infrastructure.persistence.inmemory.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import AppPro.temporalUtils.TestConstants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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