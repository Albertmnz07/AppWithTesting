package AppPro.infrastructure.input.CLI.services;

import AppPro.infrastructure.input.CLI.pages.CLIPage;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public class CLINavigator {

    private final ApplicationContext context;
    private final Screen screen;

    public CLINavigator(ApplicationContext context, Screen screen) {
        this.context = context;
        this.screen = screen;
    }

    public void goToPage(Class<? extends CLIPage> page){
        context.getBean(page).show();
    }

    public void exitApp(){
        try{
            screen.stopScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
