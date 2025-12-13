package application.usecases.user;

import domain.entities.User;
import domain.repositories.UserRepository;
import domain.repositories.UserRepositoryTestFake;
import org.junit.jupiter.api.Test;
import utils.TestConstants;


import static org.junit.jupiter.api.Assertions.*;



class CreateUserUseCaseTest {

    @Test
    void shouldCreateUser(){

        UserRepository userRepository = new UserRepositoryTestFake();

        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository);
        String name = TestConstants.USER_NAME;
        String password = TestConstants.PASSWORD;
        User user = createUserUseCase.execute(name , password);

        assertEquals(name , user.getUserName().getValue());
        assertEquals(password , user.getPassword().getValue());
        assertTrue(userRepository.findById(user.getUserId()).isPresent());



    }

}