package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.user.LogInUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.AbstractCLIPage;
import AppPro.infrastructure.input.CLI.Base.AbstractFormPage;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.adapters.ConsoleSelector;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends AbstractFormPage {

    private final SessionContext sessionContext;
    private final LogInUseCase logInUseCase;

    public LoginPage(UIManager ui, CLINavigator navigator, SessionContext sessionContext, LogInUseCase logInUseCase) {
        super(ui, navigator);
        this.sessionContext = sessionContext;
        this.logInUseCase = logInUseCase;
    }

    @Override
    public void onShow(ConsoleScrollingForm form) {
        boolean inLogin = false;
        while (inLogin){

            String userName = form.readInput("Username" , false);
            String password = form.readInput("Password" , false);

            try{

                User user = logInUseCase.execute(userName , password);
                //navigate to user main menu

            } catch(DomainException e){
                ui.showError(form , e);
            }
        }
    }
}
