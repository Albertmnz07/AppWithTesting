package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.base.AbstractFormPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

public class CreateUserPage extends AbstractFormPage {

    private final CreateUserUseCase createUserUseCase;

    public CreateUserPage(UIManager ui, CLINavigator navigator, CreateUserUseCase createUserUseCase) {
        super(ui, navigator);
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void onShow(ConsoleScrollingForm form) {
        form.printLine("Introduce your credential");
        boolean isWriting = true;

        while(isWriting){
            String username = readInputOrBack(form , "Username" , false);
            String password = readInputOrBack(form , "Password" , true);
            String confirmPassword = readInputOrBack(form , "Confirm password" , true);

            try{
                createUserUseCase.execute(username , password , confirmPassword);
                navigator.goBack();
            } catch (DomainException e){
                ui.showError(form , e);
            }

        }

    }
}
