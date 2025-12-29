package infrastructure.input.cli.pages;

import main.application.ports.InputPort;
import main.application.usecases.chat.CreateChatUseCase;
import main.application.usecases.user.FindUserByUserNameUseCase;
import main.domain.entities.Chat;
import main.domain.entities.User;
import main.domain.valueObject.UserName;
import main.infrastructure.input.CLI.ConsoleRunner;
import main.infrastructure.input.CLI.pages.HomePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import main.temporalUtils.TestConstants;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class HomePageTest {

    @Mock
    ConsoleRunner runner;

    @Mock
    User currentUser;

    @Mock
    InputPort input;

    @Mock
    UserName username;

    @Mock
    FindUserByUserNameUseCase findUserByUserNameUseCase;

    @Mock
    CreateChatUseCase createChatUseCase;

    HomePage homePage;
    @BeforeEach
    void setUp(){
        when(runner.getCurrentUser()).thenReturn(currentUser);
        when(currentUser.getUserName()).thenReturn(username);
        when(username.getValue()).thenReturn(TestConstants.USER_NAME);

        homePage = new HomePage(runner , input);
    }

    @Test
    void shouldStartNewChat(){
        when(input.readInt(any())).thenReturn(HomePage.START_CHAT , HomePage.LOGOUT);
        User searchUser = mock(User.class);
        when(input.readString(any())).thenReturn(TestConstants.USER_NAME);

        when(runner.getFindUserByUserNameUseCase()).thenReturn(findUserByUserNameUseCase);
        when(findUserByUserNameUseCase.execute(currentUser.getUserId() , TestConstants.USER_NAME)).thenReturn(searchUser);

        when(runner.getCreateChatUseCase()).thenReturn(createChatUseCase);
        when(createChatUseCase.execute(currentUser.getUserId() , searchUser.getUserId())).thenReturn(mock(Chat.class));

        homePage.show();
        verify(createChatUseCase).execute(any() , any());

    }

   /* @Test
    void shouldFailStartNewChat(){
        when(input.readInt(any())).thenReturn(HomePage.START_CHAT , HomePage.LOGOUT);
        User searchUser = mock(User.class);
        when(input.readString(any())).thenReturn(TestConstants.USER_NAME);

        when(runner.getFindUserByUserNameUseCase()).thenReturn(findUserByUserNameUseCase);
        when(findUserByUserNameUseCase.execute(currentUser.getUserId() , TestConstants.USER_NAME)).thenReturn(searchUser);

        when(runner.getCreateChatUseCase()).thenReturn(createChatUseCase);
        when(createChatUseCase.execute(any() , any())).thenThrow(new ChatAlreadyExistsException());

        homePage.show();

        verify(createChatUseCase , never()).execute(any() , any());

    }*/

    @Test
    void shouldLogOut(){
        when(input.readInt("Selection")).thenReturn(HomePage.LOGOUT);

        homePage.show();

        verify(runner).logout();
    }


}