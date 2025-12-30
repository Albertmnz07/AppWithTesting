package infrastructure.input.cli.pages;

import application.ports.InputPort;
import application.usecases.chat.CreateChatUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import domain.entities.Chat;
import domain.entities.User;
import domain.valueObject.UserName;
import infrastructure.input.CLI.ConsoleRunner;
import infrastructure.input.CLI.pages.HomePage;
import infrastructure.utils.MessageProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import temporalUtils.TestConstants;

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
    MessageProvider messageProvider;
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

        homePage = new HomePage(runner , input , messageProvider);
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