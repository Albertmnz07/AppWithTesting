package infrastructure.input.CLI;

import infrastructure.input.CLI.pages.HomePage;
import infrastructure.input.CLI.pages.WelcomePage;
import infrastructure.input.CLI.utils.SessionContext;
import org.springframework.boot.CommandLineRunner;
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
