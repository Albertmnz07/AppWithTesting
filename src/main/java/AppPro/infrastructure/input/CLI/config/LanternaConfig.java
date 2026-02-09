package AppPro.infrastructure.input.CLI.config;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;

/**
 * Spring Configuration for Lanterna Terminal User Interface (TUI) components.
 * <p>
 * This class serves aas the central factory for Lanterna resources. Its primary objetive is to integrate
 * terminal hardware abstraction into the Spring ApplicationContext, ensuring thread-safe access to the
 * console and proper lifecycle management.
 * </p>
 */
@Configuration
public class LanternaConfig {

    @Bean(destroyMethod = "close")
    public Screen lanternaScreen() throws IOException {

        try {
            Terminal terminal = createTerminalBasedOnEnvironment();

            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);

            return screen;
        } catch (IOException e) {
            throw new RuntimeException("Critical fail");
        }

    }

    public Terminal createTerminalBasedOnEnvironment() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        if (System.console() == null) {
            return factory.setForceAWTOverSwing(true)
                    .setTerminalEmulatorTitle("AppPro - Developing mode")
                    .createTerminal();
        } else {
            Terminal terminal = factory.setForceTextTerminal(true)
                    .createTerminal();

            return terminal;
        }

    }
}
