package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.usecases.message.GetChatMessagesUseCase;
import AppPro.application.usecases.message.SendMessageUseCase;
import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.domain.valueObject.ChatId;
import AppPro.infrastructure.input.CLI.base.AbstractChatPage;
import AppPro.infrastructure.input.CLI.services.CLINavigator;
import AppPro.infrastructure.input.CLI.services.UIManager;
import AppPro.infrastructure.input.CLI.utils.SessionContext;

import java.util.List;

public class PrivateChatPage extends AbstractChatPage {

    private final SessionContext sessionContext;
    private final GetChatMessagesUseCase getChatMessagesUseCase;
    private final SendMessageUseCase sendMessageUseCase;

    private ChatId targetChatId;
    private String otherUserName;

    public PrivateChatPage(UIManager ui, CLINavigator navigator,
                           SessionContext sessionContext,
                           GetChatMessagesUseCase getChatMessagesUseCase,
                           SendMessageUseCase sendMessageUseCase) {
        super(ui, navigator);
        this.sessionContext = sessionContext;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
    }

    public void setTarget(ChatId chatId, String otherUserName){
        this.targetChatId = chatId;
        this.otherUserName = otherUserName;
    }

    @Override
    protected User getCurrentUser(){
        return sessionContext.getCurrentUser();
    }

    @Override
    protected String getChatTitle(){
        return otherUserName;
    }

    @Override
    protected List<Message> getMessageHistory() {
        return getChatMessagesUseCase.execute(targetChatId);
    }

    @Override
    protected void onSendMessage(String text) {
        sendMessageUseCase.execute(
                targetChatId,
                sessionContext.getCurrentUser().getUserId(),
                text
        );
    }
}
