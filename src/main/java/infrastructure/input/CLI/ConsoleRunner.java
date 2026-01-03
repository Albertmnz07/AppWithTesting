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
import infrastructure.utils.MessageProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final WelcomePage welcomePage;
    private final HomePage homePage;

    User currentUser = null;
    boolean isRunning = true;

    public ConsoleRunner(WelcomePage welcomePage , HomePage homePage){
        this.welcomePage = welcomePage;
        this.homePage = homePage;
    }

    @Override
    public void run(String... args) throws Exception {
        while (isRunning){
            if (currentUser == null){
                welcomePage.show(this); //Its necessary give the runner instance to access login and logout
            } else {
                homePage.show(this);
            }
        }
    }


    public void login(User user){this.currentUser = user;}
    public void logout(){this.currentUser = null;}
    public void exit(){
        this.isRunning = false;
        System.out.println("Thanks for using");}

    public User getCurrentUser() {
        return currentUser;
    }

}
