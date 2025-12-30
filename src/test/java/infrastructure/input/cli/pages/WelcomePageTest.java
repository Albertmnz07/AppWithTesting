package infrastructure.input.cli.pages;

import application.ports.InputPort;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.LogInUseCase;
import domain.entities.User;
import domain.exceptions.password.PasswordTooShortException;
import domain.exceptions.user.UserNotFoundException;
import infrastructure.input.CLI.ConsoleRunner;
import infrastructure.input.CLI.pages.WelcomePage;
import infrastructure.utils.MessageProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temporalUtils.TestConstants;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WelcomePageTest {

    @Mock
    ConsoleRunner runner;

    @Mock
    InputPort input;

    @Mock
    MessageProvider messageProvider;

    @Mock
    LogInUseCase logInUseCase;

    @Mock
    CreateUserUseCase createUserUseCase;

    WelcomePage welcomePage;

    @BeforeEach
    void setUp(){
        welcomePage = new WelcomePage(runner , input , messageProvider);
    }
    @Test
    void shouldLogIn(){
        when(input.readInt(anyString())).thenReturn(WelcomePage.LOGIN);

        when(input.readString("Username")).thenReturn(TestConstants.USER_NAME);
        when(input.readString("Password")).thenReturn(TestConstants.PASSWORD);

        when(runner.getLogInUseCase()).thenReturn(logInUseCase);
        User user = mock(User.class);
        when(logInUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD)).thenReturn(user);

        welcomePage.show();

        verify(runner).login(user);

    }

    @Test
    void shouldFailLogIn(){
        when(input.readInt(anyString())).thenReturn(WelcomePage.LOGIN);

        when(input.readString("Username")).thenReturn(TestConstants.USER_NAME);
        when(input.readString("Password")).thenReturn(TestConstants.PASSWORD);

        when(runner.getLogInUseCase()).thenReturn(logInUseCase);

        when(logInUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD)).thenThrow(new UserNotFoundException());

        welcomePage.show();
        verify(runner , never()).login(any());
    }

    @Test
    void shouldCreateAccount(){
        when(input.readInt(any())).thenReturn(WelcomePage.CREATE_ACCOUNT);

        when(input.readString("Username")).thenReturn(TestConstants.USER_NAME);
        when(input.readString("Password")).thenReturn(TestConstants.PASSWORD);

        when(runner.getCreateUserUseCase()).thenReturn(createUserUseCase);
        User user = mock(User.class);
        when(createUserUseCase.execute(TestConstants.USER_NAME , TestConstants.PASSWORD)).thenReturn(user);

        welcomePage.show();
        verify(runner).login(user);
    }

     @Test
    void shouldFailCreateAccount(){
         when(input.readInt(any())).thenReturn(WelcomePage.CREATE_ACCOUNT);

         when(input.readString("Username")).thenReturn(TestConstants.USER_NAME);
         when(input.readString("Password")).thenReturn(TestConstants.PASSWORD);

         when(runner.getCreateUserUseCase()).thenReturn(createUserUseCase);

         when(createUserUseCase.execute(anyString() , anyString())).thenThrow(new PasswordTooShortException());

         welcomePage.show();
         verify(runner , never()).login(any());
     }

     @Test
    void shouldExitProgram(){
        when(input.readInt(any())).thenReturn(WelcomePage.EXIT);

        welcomePage.show();

        verify(runner).exit();

     }

}