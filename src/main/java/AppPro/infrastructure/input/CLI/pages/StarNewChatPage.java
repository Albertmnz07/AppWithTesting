package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.chat.GetOrCreateChatUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.User;
import AppPro.domain.service.UserFinder;
import AppPro.infrastructure.input.CLI.base.AbstractSearchSelector;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.ChatContext;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StarNewChatPage extends AbstractSearchSelector<User> {

    private final UserFinder userFinder;
    private final ChatContext chatContext;
    private final SessionContext sessionContext;
    private final GetOrCreateChatUseCase getOrCreateChatUseCase;

    public StarNewChatPage(UIManager ui, CLINavigator navigator, UserFinder userFinder, ChatContext chatContext, SessionContext sessionContext, GetOrCreateChatUseCase getOrCreateChatUseCase) {
        super(ui, navigator);
        this.userFinder = userFinder;
        this.chatContext = chatContext;
        this.sessionContext = sessionContext;
        this.getOrCreateChatUseCase = getOrCreateChatUseCase;
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
        User currentUser = sessionContext.getCurrentUser();

        Chat chat = getOrCreateChatUseCase.execute(currentUser.getUserId() , item.getUserId());

        chatContext.setCurrentUser(item);
        chatContext.setCurrentChatId(chat.getChatId());

        navigator.goToPage(PrivateChatPage.class);
    }
}
