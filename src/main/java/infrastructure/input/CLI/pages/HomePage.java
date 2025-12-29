package main.java.infrastructure.input.CLI.pages;

import main.java.application.ports.InputPort;
import main.java.domain.entities.Chat;
import main.java.domain.entities.User;
import main.java.domain.exceptions.DomainException;import main.java.infrastructure.input.CLI.ConsoleRunner;
import main.java.infrastructure.input.CLI.utils.CliErrorMessage;

public class HomePage {

    private final ConsoleRunner runner;
    private final User user;
    private final InputPort input;

    public static final int CHECK_CHATS = 1;
    public static final int START_CHAT = 2;
    public static final int CONFIGURATION = 3;
    public static final int LOGOUT = 4;

    public HomePage(ConsoleRunner runner , InputPort input){
        this.runner = runner;
        this.input = input;
        this.user = runner.getCurrentUser();
    }

    public void show(){
        boolean isOnPage = true;

        while (isOnPage){

            System.out.println("Main menu, welcome " + user.getUserName().getValue());
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
                case CONFIGURATION -> handleConfiguration();
                case LOGOUT -> {
                    runner.logout();
                    isOnPage = false;
                }
                default -> System.out.println("Please, select a correct option");
            }
        }

    }

    public void handleCheckChats(){

    }

    public void handleStartChat(){
        System.out.println("Adding new chat");
        String username = input.readString("Please insert the username");

        try{
            User newUser = runner.getFindUserByUserNameUseCase().execute(user.getUserId() , username);
            Chat chat = runner.getCreateChatUseCase().execute(this.user.getUserId() , newUser.getUserId());
        } catch(DomainException e){
            System.out.println(CliErrorMessage.from(e.getCode()));
        }
    }

    public void handleConfiguration(){

    }

}
