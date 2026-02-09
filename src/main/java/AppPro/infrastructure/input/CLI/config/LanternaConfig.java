package AppPro.infrastructure.input.CLI.config;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

    /**
     * Create a @{@link Terminal} after checking what type of environment is running.
     * <p>
     *     If there is no console({@code System.console() == null}), creates an "emulator"
     *     like a developing mode. In production, console exists so there is a real console
     *     to run the CLI. <br>
     *     This method also wraps the main {@code Thread} in another one which starts when app
     *     is force to exit and main {@code Thread} is blocked.
     * </p>
     * @return terminal ready be wrapped by a {@link Screen}
     * @throws IOException cannot close application
     */
    public Terminal createTerminalBasedOnEnvironment() throws IOException {
        DefaultTerminalFactory factory = new DefaultTerminalFactory();

        if (System.console() == null) { //there is no real console

            Terminal terminal = factory
                    .setTerminalEmulatorTitle("Developing mode") //title to specify developer mode
                    .createTerminal();
            /*Lanterna can work in only text mode, here we make sure that we are working with a
            window(awt) and at the same time doing a cast to use specific methods.
            If user never tries to force exit, this thread is never created, so all processes are successfully finished*/
            if (terminal instanceof Window window){
                window.addWindowListener(new WindowAdapter(){  //add an event listener, specific for windows
                    @Override
                    public void windowClosing(WindowEvent e){//choose the Window listener we are interested in
                        new Thread(() -> { //this thread is independent of the main flow, if applications stop, it keeps alive
                            try{
                                System.exit(0);
                            } catch (Exception ex){
                                Runtime.getRuntime().halt(0); //if secure stop fails, forcefully kill all proccess
                            }
                        }).start();
                    }
                });
            }
            return terminal;
        } else {
            Terminal terminal = factory.createTerminal();

            return terminal;
        }

    }
}
