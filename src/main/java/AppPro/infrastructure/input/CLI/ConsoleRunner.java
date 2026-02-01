package AppPro.infrastructure.input.CLI;

import AppPro.infrastructure.input.CLI.pages.WelcomePage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CLINavigator navigator;

    public ConsoleRunner(CLINavigator navigator){
        this.navigator = navigator;
    }

    @Override
    public void run(String... args) throws Exception {

        navigator.goToPage(WelcomePage.class);

    }


}
