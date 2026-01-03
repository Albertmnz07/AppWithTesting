package infrastructure.input.CLI;

import application.ports.InputPort;
import application.usecases.chat.CreateChatUseCase;
import application.usecases.chat.GetUserChatsUseCase;
import application.usecases.message.GetChatMessagesUseCase;
import application.usecases.message.SendMessageUseCase;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import application.usecases.user.LogInUseCase;
import domain.entities.User;
import infrastructure.input.CLI.pages.HomePage;
import infrastructure.input.CLI.pages.WelcomePage;
import infrastructure.input.CLI.utils.SessionContext;
import infrastructure.utils.MessageProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final WelcomePage welcomePage;
    private final HomePage homePage;
    private final SessionContext sessionContext;

    boolean isRunning = true;

    public ConsoleRunner(WelcomePage welcomePage , HomePage homePage , SessionContext sessionContext){
        this.welcomePage = welcomePage;
        this.homePage = homePage;
        this.sessionContext = sessionContext;
    }

    @Override
    public void run(String... args) throws Exception {
        while (isRunning){
            if (sessionContext.getCurrentUser() == null){
                welcomePage.show(this::exit); //Its necessary give the runner instance to access login and logout
            } else {
                homePage.show();
            }
        }
    }

    public void exit(){
        this.isRunning = false;
        System.out.println("Thanks for using");}


}
