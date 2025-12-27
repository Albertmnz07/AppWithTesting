package infrastructure.input.cli.pages;

import main.application.ports.InputPort;
import main.application.usecases.user.LogInUseCase;
import main.domain.entities.User;
import main.domain.exceptions.DomainException;
import main.domain.exceptions.user.UserNotFoundException;
import main.infrastructure.input.cli.ConsoleRunner;
import main.infrastructure.input.cli.pages.WelcomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.TestConstants;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WelcomePageTest {

    @Mock
    ConsoleRunner runner;

    @Mock
    InputPort input;

    @Mock
    LogInUseCase logInUseCase;

    WelcomePage welcomePage;

    @BeforeEach
    void setUp(){
        welcomePage = new WelcomePage(runner , input);
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

}