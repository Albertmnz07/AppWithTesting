package infrastructure.input.CLI;

import application.ports.InputPort;
import application.usecases.chat.CreateChatUseCase;
import application.usecases.chat.GetUserChatsUseCase;
import application.usecases.message.GetChatMessagesUseCase;
import application.usecases.message.SendMessageUseCase;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.FindUserByUserNameUseCase;
import application.usecases.user.LogInUseCase;
import domain.entities.User;
import infrastructure.input.CLI.pages.HomePage;
import infrastructure.input.CLI.pages.WelcomePage;

public class ConsoleRunner {

    User currentUser = null;
    boolean isRunning = true;

    CreateUserUseCase createUserUseCase;
    LogInUseCase logInUseCase;
    FindUserByUserNameUseCase findUserByUserNameUseCase;
    SendMessageUseCase sendMessageUseCase;
    GetChatMessagesUseCase getChatMessagesUseCase;
    CreateChatUseCase createChatUseCase;
    GetUserChatsUseCase getUserChatsUseCase;
    InputPort input;

    public ConsoleRunner(CreateUserUseCase createUserUseCase, LogInUseCase logInUseCase , FindUserByUserNameUseCase findUserByUserNameUseCase , SendMessageUseCase sendMessageUseCase ,
                         GetChatMessagesUseCase getChatMessagesUseCase , CreateChatUseCase createChatUseCase , GetUserChatsUseCase getUserChatsUseCase , InputPort input){
        this.createUserUseCase = createUserUseCase;
        this.logInUseCase = logInUseCase;
        this.findUserByUserNameUseCase = findUserByUserNameUseCase;
        this.sendMessageUseCase = sendMessageUseCase;
        this.getChatMessagesUseCase = getChatMessagesUseCase;
        this.createChatUseCase = createChatUseCase;
        this.getUserChatsUseCase = getUserChatsUseCase;
        this.input = input;
    }

    public void run(){
        while (isRunning){
            if (currentUser == null){
                new WelcomePage(this , this.input).show();
            } else {
                new HomePage(this , input).show();
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

    public FindUserByUserNameUseCase getFindUserByUserNameUseCase(){return findUserByUserNameUseCase;}


}
