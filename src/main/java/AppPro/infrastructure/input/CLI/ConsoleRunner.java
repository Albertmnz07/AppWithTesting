package AppPro.infrastructure.input.CLI;

import AppPro.infrastructure.input.CLI.pages.WelcomePage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Application entry point component that bridges the Spring Boot startup process with the Lanterna-base Terminal User Interface(TUI)
 * <p>
 *     This class implements {@link CommandLineRunner}, ensuring that the terminal interface is launched automatically once the Spring Application Context
 *     is fully initialized.
 * </p>
 *
 * @author Albertmnz07/AppPro
 */
@Component
public class ConsoleRunner implements CommandLineRunner {

    private final CLINavigator navigator;

    /**
     * Initializes the runner with the required navigation service
     * @param navigator The service responsible for managing page transitions and history.
     */
    public ConsoleRunner(CLINavigator navigator){
        this.navigator = navigator;
    }

    /**
     * Execute the initial transition to the application's landing page.
     * <p>
     *     This method is invoked by the Spring framework after all beans are instantiated.
     *     It hands over the application control flow to the {@link CLINavigator},
     *     starting with the {@link WelcomePage}.
     * </p>
     * @param args arg Command line arguments passed to the application context (not used).
     */
    @Override
    public void run(String... args) {

        navigator.goToPage(WelcomePage.class);

    }


}
