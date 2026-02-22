package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.user.LogInUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.base.AbstractFormPage;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.LanternaMessagePresenter;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import org.springframework.stereotype.Component;

@Component
public class LoginPage extends AbstractFormPage {

    private final SessionContext sessionContext;
    private final LogInUseCase logInUseCase;

    public LoginPage(UIManager ui, CLINavigator navigator, SessionContext sessionContext, LogInUseCase logInUseCase , LanternaMessagePresenter presenter) {
        super(ui, navigator , presenter);
        this.sessionContext = sessionContext;
        this.logInUseCase = logInUseCase;
    }

    @Override
    public void onShow(ConsoleScrollingForm form) {
        boolean inLogin = true;
        while (inLogin){

            String userName = readInputOrBack(form , "Username" , false);
            String password = readInputOrBack(form , "Password" , false);

            try{

                User user = logInUseCase.execute(userName , password);
                sessionContext.setUser(user);
                inLogin = false;
                navigator.goToPage(UserMenuPage.class);

            } catch(DomainException e){
                showError(form , e);
            }
        }
    }
}
