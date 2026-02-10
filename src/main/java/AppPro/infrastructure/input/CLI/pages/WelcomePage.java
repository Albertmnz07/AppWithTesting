package AppPro.infrastructure.input.CLI.pages;

import AppPro.infrastructure.input.CLI.base.AbstractSelectorPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
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
