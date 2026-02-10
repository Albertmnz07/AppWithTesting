package AppPro.infrastructure.input.CLI.base;

import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

public abstract class AbstractFormPage extends AbstractCLIPage {

    public AbstractFormPage(UIManager ui, CLINavigator navigator) {
        super(ui, navigator);
    }

    @Override
    public void runSafe() {
        ConsoleScrollingForm form = ui.createForm();
        onShow(form);
    }

    public abstract void onShow(ConsoleScrollingForm form);

    protected String readInputOrBack(ConsoleScrollingForm form, String prompt, boolean isPassword) {
        return form.readInput(prompt, isPassword);
    }
}
