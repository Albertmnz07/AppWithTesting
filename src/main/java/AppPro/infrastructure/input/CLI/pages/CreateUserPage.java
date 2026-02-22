package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.user.CreateUserUseCase;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.adapters.ConsoleScrollingForm;
import AppPro.infrastructure.input.CLI.base.AbstractFormPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.LanternaMessagePresenter;
import org.springframework.stereotype.Component;

@Component
public class CreateUserPage extends AbstractFormPage {

    private final CreateUserUseCase createUserUseCase;

    public CreateUserPage(UIManager ui, CLINavigator navigator, CreateUserUseCase createUserUseCase , LanternaMessagePresenter presenter) {
        super(ui, navigator , presenter);
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
                showError(form , e);
            }

        }

    }
}
