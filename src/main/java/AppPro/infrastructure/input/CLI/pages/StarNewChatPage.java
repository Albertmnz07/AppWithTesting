package AppPro.infrastructure.input.CLI.pages;

import AppPro.domain.entities.User;
import AppPro.domain.service.UserFinder;
import AppPro.infrastructure.input.CLI.base.AbstractSearchSelector;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;

import java.util.List;

public class StarNewChatPage extends AbstractSearchSelector<User> {

    private final UserFinder userFinder;

    public StarNewChatPage(UIManager ui, CLINavigator navigator, UserFinder userFinder) {
        super(ui, navigator);
        this.userFinder = userFinder;
    }

    @Override
    protected List<User> search(String query) {
        return userFinder.findAll(query);
    }

    @Override
    protected String getLabel(User item) {
        return item.getUserName().getValue();
    }

    @Override
    protected void onSelect(User item) {
        System.out.println("Get into chat with " + item.getUserName().getValue());
    }
}
