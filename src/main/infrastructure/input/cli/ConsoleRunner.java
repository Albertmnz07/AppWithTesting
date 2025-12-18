package main.infrastructure.input.cli;

import main.application.usecases.chat.CreateChatUseCase;
import main.application.usecases.chat.GetUserChatsUseCase;
import main.application.usecases.message.GetChatMessagesUseCase;
import main.application.usecases.message.SendMessageUseCase;
import main.application.usecases.user.CreateUserUseCase;
import main.application.usecases.user.LogInUseCase;
import main.domain.entities.User;
import main.infrastructure.input.cli.pages.WelcomePage;

public class ConsoleRunner {

    User currentUser = null;
    boolean isRunning = true;

    CreateUserUseCase createUserUseCase;
    LogInUseCase logInUseCase;
    SendMessageUseCase sendMessageUseCase;
    GetChatMessagesUseCase getChatMessagesUseCase;
    CreateChatUseCase createChatUseCase;
    GetUserChatsUseCase getUserChatsUseCase;

    public ConsoleRunner(CreateUserUseCase createUserUseCase, LogInUseCase logInUseCase , SendMessageUseCase sendMessageUseCase ,
                         GetChatMessagesUseCase getChatMessagesUseCase , CreateChatUseCase createChatUseCase , GetUserChatsUseCase getUserChatsUseCase){
        this.createUserUseCase = createUserUseCase;
        this.logInUseCase = logInUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.createChatUseCase = createChatUseCase;
        this.getUserChatsUseCase = getUserChatsUseCase;
    }

    public void run(){
        while (isRunning){
            if (currentUser == null){
                new WelcomePage(this).show();
            } else {
                //add home page
            }
        }
    }

    public void login(User user){this.currentUser = user;}
    public void logout(){this.currentUser = null;}
    public void exit(){
        this.isRunning = false;
        System.out.println("Thanks for using");}

    public User getCurrentUser() {
        return currentUser;
    }

    public CreateUserUseCase getCreateUserUseCase() {
        return createUserUseCase;
    }

    public LogInUseCase getLogInUseCase() {
        return logInUseCase;
    }

    public SendMessageUseCase getSendMessageUseCase() {
        return sendMessageUseCase;
    }

    public GetChatMessagesUseCase getGetChatMessagesUseCase() {
        return getChatMessagesUseCase;
    }

    public CreateChatUseCase getCreateChatUseCase() {
        return createChatUseCase;
    }

    public GetUserChatsUseCase getGetUserChatsUseCase() {
        return getUserChatsUseCase;
    }


}
