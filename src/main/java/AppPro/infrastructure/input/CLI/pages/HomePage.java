package AppPro.infrastructure.input.CLI.pages;

import AppPro.application.ports.InputPort;
import AppPro.application.usecases.chat.CreateChatUseCase;
import AppPro.application.usecases.chat.GetUserChatsUseCase;
import AppPro.application.usecases.user.FindUserByUserNameUseCase;
import AppPro.domain.entities.Chat;
import AppPro.domain.entities.User;
import AppPro.domain.exceptions.DomainException;
import AppPro.infrastructure.input.CLI.utils.SessionContext;
import AppPro.infrastructure.utils.MessageProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HomePage {

    private final User user;
    private final InputPort input;
    private final MessageProvider messageProvider;
    private final SessionContext sessionContext;
    private final GetUserChatsUseCase getUserChatsUseCase;
    private final FindUserByUserNameUseCase findUserByUserNameUseCase;
    private final CreateChatUseCase createChatUseCase;
    private final ChatPage chatPage;

    public static final int CHECK_CHATS = 1;
    public static final int START_CHAT = 2;
    public static final int CONFIGURATION = 3;
    public static final int LOGOUT = 4;

    public HomePage(InputPort input , MessageProvider messageProvider , SessionContext sessionContext , GetUserChatsUseCase getUserChatsUseCase
     , FindUserByUserNameUseCase findUserByUserNameUseCase , CreateChatUseCase createChatUseCase , ChatPage chatPage){
        this.input = input;
        this.messageProvider = messageProvider;
        this.user = sessionContext.getCurrentUser();
        this.sessionContext = sessionContext;
        this.getUserChatsUseCase = getUserChatsUseCase;
        this.findUserByUserNameUseCase = findUserByUserNameUseCase;
        this.createChatUseCase = createChatUseCase;
        this.chatPage = chatPage;
    }

    public void show(){
        boolean isOnPage = true;

        while (isOnPage){

            System.out.println("====AppPro.Main menu, welcome " + user.getUserName().getValue() + "====");
            System.out.println("Please, select one of this options");
            System.out.println("""
                1. Check my chats
                2. Star new chat
                3. Configuration
                4. Log out
                """);
            int selection = input.readInt("Selection");

            switch (selection){
                case CHECK_CHATS -> handleCheckChats();
                case START_CHAT -> handleStartChat();
                case CONFIGURATION -> System.out.println("Unimplemented");
                case LOGOUT -> {
                    sessionContext.setUser(null);
                    isOnPage = false;
                }
                default -> System.out.println("Please, select a correct option");
            }
        }

    }

    public void handleCheckChats(){
        System.out.println("====Your chats====");
        List<Chat> chatList = getUserChatsUseCase.execute(user.getUserId());
        for (int i = 0 ; i < chatList.size() ; i++){
            System.out.println((i + 1) + ". " + chatList.get(i));
        }
        try{
            int selectedChat = input.readInt("Enter the chat number or 0 to return to the main menu");
            chatPage.show(chatList.get(selectedChat - 1));
        } catch (DomainException e){
            messageProvider.getError(e);
        }



    }

    public void handleStartChat(){
        System.out.println("====Adding new chat====");
        String username = input.readString("Please insert the username");

        try{
            User newUser = findUserByUserNameUseCase.execute(user.getUserId() , username);
            Chat chat = createChatUseCase.execute(this.user.getUserId() , newUser.getUserId());
        } catch(DomainException e){
            System.out.println(messageProvider.getError(e));
        }
    }

}
