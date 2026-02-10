package AppPro.infrastructure.input.CLI.base;

import AppPro.infrastructure.input.CLI.exceptions.BackNavigationException;
import AppPro.infrastructure.input.CLI.pages.CLIPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

/**
 * Base abstraction for all CLI-based pages within the application.
 * <p>
 *     This class provides shared access to essential UI and Navigation services.
 * </p>
 */
public abstract class AbstractCLIPage implements CLIPage {

    /**
     * Service used to interact with terminal adapters
     */
    protected final UIManager ui;

    /**
     * Service used to manage the transactions between different application states.
     */
    protected final CLINavigator navigator;

    /**
     * Constructor for injecting core infrastructure services
     * @param ui The UI manager responsible for rendering.
     * @param navigator The navigator responsible for flow control
     */
    public AbstractCLIPage(UIManager ui, CLINavigator navigator) {
        this.ui = ui;
        this.navigator = navigator;
    }

    public abstract void runSafe();

    public void show(){
        try{
            runSafe();
        } catch(BackNavigationException e){
            navigator.goBack();
        }
    }
}
