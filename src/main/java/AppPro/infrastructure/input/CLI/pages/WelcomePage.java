package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.ports.InputPort;
import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.application.usecases.user.LogInUseCase;
import AppPro.domain.entities.User;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.AbstractCLIPage;
import AppPro.infrastructure.input.CLI.Base.AbstractSelectorPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import AppPro.infrastructure.utils.MessageProvider;
import org.springframework.stereotype.Component;

@Component
public class WelcomePage extends AbstractSelectorPage {

    public WelcomePage(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void onShow() {
        createSelector("Welcome, choose an option").
                addItem(() -> navigator.goToPage(LoginPage.class) , "Login")
                .addItem(() -> System.out.println("Create") , "Create account")
                .show();
    }
}
