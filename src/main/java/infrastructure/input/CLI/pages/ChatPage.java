package infrastructure.input.CLI.pages;

import application.ports.InputPort;
import application.usecases.message.SendMessageUseCase;
import infrastructure.input.CLI.utils.SessionContext;
import infrastructure.utils.MessageProvider;

public class ChatPage {

    private final SessionContext sessionContext;
    private final InputPort port;
    private final MessageProvider messageProvider;
    private final SendMessageUseCase sendMessageUseCase;

    public ChatPage(SessionContext sessionContext, InputPort port, MessageProvider messageProvider, SendMessageUseCase sendMessageUseCase) {
        this.sessionContext = sessionContext;
        this.port = port;
        this.messageProvider = messageProvider;
        this.sendMessageUseCase = sendMessageUseCase;

    }



}
