package AppPro.infrastructure.input.CLI.config;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * Spring Configuration for Lanterna Terminal User Interface (TUI) components.
 * <p>
 *     This class serves aas the central factory for Lanterna resources. Its primary objetive is to integrate
 *     terminal hardware abstraction into the Spring ApplicationContext, ensuring thread-safe access to the
 *     console and proper lifecycle management.
 * </p>
 */
@Configuration
public class LanternaConfig {

    /**
     * Initializes and provides a singleton {@link Screen} bean.
     * <p>
     *     The {@code destroyMehod = "close"} attribute is critical. It ensures that upon application shutdown
     *     (graceful or otherwise), Spring invokes the terminal's restoration routine. This prevents "terminal
     *     corruption" by returning the user's console to its original state(restoring the cursor,
     *     color, and standard input mode);
     * </p>
     *
     * @return A pre-initialized {@link Screen} instance ready for rendering.
     * @throws IOException If the underlying physical terminal cannot be accessed.
     */
    @Bean(destroyMethod = "close")
    public Screen lanternaScreen() throws IOException{

        //Detect and create the physical terminal connection(depending of OS)
        Terminal terminal = new DefaultTerminalFactory().createTerminal();

        //Wrap the terminal in a logical screen layer for optimized rendering.
        Screen screen = new TerminalScreen(terminal);

        screen.startScreen();

        //Hides the cursor(optional)
        screen.setCursorPosition(null);

        return screen;
    }
}
