package AppPro.infrastructure.input.CLI.services;

import AppPro.infrastructure.input.CLI.pages.CLIPage;
import com.googlecode.lanterna.screen.Screen;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public class CLINavigator {

    private final ApplicationContext context;
    private final Stack<Class<? extends CLIPage>> history = new Stack<>();

    public CLINavigator(ApplicationContext context) {
        this.context = context;
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
