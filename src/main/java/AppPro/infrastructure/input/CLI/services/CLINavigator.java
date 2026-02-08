package AppPro.infrastructure.input.CLI.services;

import AppPro.infrastructure.input.CLI.pages.CLIPage;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Stack;

@Component
public class CLINavigator {

    private final ApplicationContext context;
    private final Stack<Class<? extends CLIPage>> history = new Stack<>();

    public CLINavigator(ApplicationContext context, Screen screen) {
        this.context = context;
        this.screen = screen;
    }

    public void goToPage(Class<? extends CLIPage> page){
        history.push(page);
        executePage(page);

    }

    public void goBack(){
        if (history.size() > 1){
            history.pop();
            executePage(history.peek());
        } else{
            System.exit(0);
        }
    }

    private void executePage(Class<? extends CLIPage> pageClass){
        CLIPage page = context.getBean(pageClass);
        page.show();
    }
}
