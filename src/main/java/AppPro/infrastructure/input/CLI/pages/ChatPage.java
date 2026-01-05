package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.ports.InputPort;
import AppPro.application.usecases.message.GetChatMessagesUseCase;
import AppPro.application.usecases.message.SendMessageUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.Message;
import AppPro.domain.entities.User;
import AppPro.domain.exceptions.DomainException;
import AppPro.domain.service.UserFinder;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import AppPro.infrastructure.utils.MessageProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatPage {

    private final SessionContext sessionContext;
    private final InputPort input;
    private final MessageProvider messageProvider;
    private final UserFinder userFinder;
    private final SendMessageUseCase sendMessageUseCase;
    private final GetChatMessagesUseCase getChatMessagesUseCase;
    private final User currentUser;

    public ChatPage(SessionContext sessionContext, InputPort input, MessageProvider messageProvider, SendMessageUseCase sendMessageUseCase,
                    GetChatMessagesUseCase getChatMessagesUseCase , UserFinder userFinder) {
        this.sessionContext = sessionContext;
        this.input = input;
        this.messageProvider = messageProvider;
        this.sendMessageUseCase = sendMessageUseCase;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.userFinder = userFinder;
        this.currentUser = sessionContext.getCurrentUser();
    }

    public void show(Chat chat){
        boolean onPage = true;


        while(onPage){
            loadMessages(getChatMessagesUseCase.execute(chat.getChatId()));

            try{
                String strMessage = input.readString("Send message");
                if (strMessage.equals("-1")){
                    onPage = false;
                    return;
                }
                sendMessageUseCase.execute(chat.getChatId() , currentUser.getUserId() , strMessage);
            } catch (DomainException e){
                messageProvider.getError(e);
            }
        }
    }

    public String loadMessages(List<Message> messageList){
        String formatMessages = "Your chat";
        for (int i = 0 ; i < messageList.size() ; i++){
            Message currentMessage = messageList.get(i);
            formatMessages += i + "  " + userFinder.find(currentMessage.getSenderId()) + ": " + currentMessage.getMessageContent();
        }
        return formatMessages;
    }



}
