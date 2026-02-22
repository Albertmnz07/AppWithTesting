package AppPro.infrastructure.input.CLI.base;

import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.LanternaMessagePresenter;

public abstract class AbstractFormPage extends AbstractCLIPage {

    private final LanternaMessagePresenter presenter;

    public AbstractFormPage(UIManager ui, CLINavigator navigator , LanternaMessagePresenter presenter) {
        super(ui, navigator);
        this.presenter = presenter;
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

    protected void showError(ConsoleScrollingForm form , DomainException e){
        presenter.printError(form , e);
    }
}
