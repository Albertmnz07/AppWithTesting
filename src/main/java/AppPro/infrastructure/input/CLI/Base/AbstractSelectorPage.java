package AppPro.infrastructure.input.CLI.Base;

import AppPro.infrastructure.input.CLI.AbstractCLIPage;
import AppPro.infrastructure.input.CLI.adapters.ConsoleSelector;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

public abstract class AbstractSelectorPage extends AbstractCLIPage {

    public AbstractSelectorPage(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void runSafe() {
        onShow();
    }

    public abstract void onShow();

    protected ConsoleSelector createSelector(String title){
        return ui.createSelector(title);
    }
}
