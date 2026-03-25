package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.message.GetChatMessagesUseCase;
import AppPro.application.usecases.message.SendMessageUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.domain.valueObject.ChatId;
import AppPro.infrastructure.input.CLI.base.AbstractChatPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.ChatContext;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrivateChatPage extends AbstractChatPage {

    private final SessionContext sessionContext;
    private final ChatContext chatContext;
    private final GetChatMessagesUseCase getChatMessagesUseCase;
    private final SendMessageUseCase sendMessageUseCase;


    public PrivateChatPage(UIManager ui, CLINavigator navigator,
                           SessionContext sessionContext,
                           ChatContext chatContext,
                           GetChatMessagesUseCase getChatMessagesUseCase,
                           SendMessageUseCase sendMessageUseCase) {
        super(ui, navigator);
        this.sessionContext = sessionContext;
        this.chatContext = chatContext;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
    }

    @Override
    protected User getCurrentUser(){
        return sessionContext.getCurrentUser();
    }

    @Override
    protected String getChatTitle(){
        return chatContext.getCurrentUser().getUserName().getValue();
    }

    @Override
    protected List<Message> getMessageHistory() {
        return getChatMessagesUseCase.execute(chatContext.getCurrentChatId());
    }

    @Override
    protected void onSendMessage(String text) {
        sendMessageUseCase.execute(
                chatContext.getCurrentChatId(),
                sessionContext.getCurrentUser().getUserId(),
                text
        );
    }
}
