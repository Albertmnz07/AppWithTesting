package infrastructure.input.CLI.pages;

import application.ports.InputPort;
import application.usecases.message.GetChatMessagesUseCase;
import application.usecases.message.SendMessageUseCase;
import domain.entities.Message;
import domain.entities.User;
import domain.service.UserFinder;
import infrastructure.input.CLI.utils.SessionContext;
import infrastructure.utils.MessageProvider;
import domain.entities.Chat;

import java.util.List;

public class ChatPage {

    private final SessionContext sessionContext;
    private final InputPort port;
    private final MessageProvider messageProvider;
    private final UserFinder userFinder;
    private final SendMessageUseCase sendMessageUseCase;
    private final GetChatMessagesUseCase getChatMessagesUseCase;
    private final User currentUser;

    public ChatPage(SessionContext sessionContext, InputPort port, MessageProvider messageProvider, SendMessageUseCase sendMessageUseCase,
                    GetChatMessagesUseCase getChatMessagesUseCase , UserFinder userFinder) {
        this.sessionContext = sessionContext;
        this.port = port;
        this.messageProvider = messageProvider;
        this.sendMessageUseCase = sendMessageUseCase;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.userFinder = userFinder;
        this.currentUser = sessionContext.getCurrentUser();
    }

    public void show(Chat chat){
        List<Message> messageList = getChatMessagesUseCase.execute(chat.getChatId());
        loadMessages(messageList);
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
