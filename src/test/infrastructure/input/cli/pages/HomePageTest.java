package infrastructure.input.cli.pages;

import main.application.ports.InputPort;
import main.domain.entities.User;
import main.domain.valueObject.UserName;
import main.infrastructure.input.cli.ConsoleRunner;
import main.infrastructure.input.cli.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import main.temporalUtils.TestConstants;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
class HomePageTest {

    @Mock
    ConsoleRunner runner;

    @Mock
    User user;

    @Mock
    InputPort input;

    @Mock
    UserName username;

    HomePage homePage;
    @BeforeEach
    void setUp(){
        when(runner.getCurrentUser()).thenReturn(user);
        when(user.getUserName()).thenReturn(username);
        when(username.getValue()).thenReturn(TestConstants.USER_NAME);

        homePage = new HomePage(runner , input);
    }

    @Test
    void shouldLogOut(){
        when(input.readInt("Selection")).thenReturn(HomePage.LOGOUT);

        homePage.show();

        verify(runner).logout();
    }


}