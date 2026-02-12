package AppPro.infrastructure.input.CLI.pages;

import AppPro.infrastructure.input.CLI.base.AbstractSelectorPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

public class UserMenuPage extends AbstractSelectorPage {

    public UserMenuPage(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void onShow() {
        createSelector("Select an option").show();

    }
}
