package main.infrastructure.input.cli.pages;

import main.domain.entities.User;
import main.infrastructure.input.cli.ConsoleRunner;
import main.infrastructure.input.cli.utils.InputReader;

public class HomePage {

    ConsoleRunner runner;
    User user = runner.getCurrentUser();

    public static final int CHECK_CHATS = 1;
    public static final int START_CHAT = 2;
    public static final int CONFIGURATION = 3;
    public static final int LOGOUT = 4;

    public HomePage(ConsoleRunner runner){
        this.runner = runner;
    }

    public void show(){
        System.out.println("Main menu, welcome " + user.getUserName().getValue());
        System.out.println("Please, select one of this options");
        System.out.println("""
                1. Check my chats
                2. Star new chat
                3. Configuration
                4. Log out
                """);
        int selection = InputReader.readInt("Selection");

        switch (selection){
            case CHECK_CHATS -> handleCheckChats();
            case START_CHAT -> handleStartChat();
            case CONFIGURATION -> handleConfiguration();
            case LOGOUT -> runner.logout();
        }
    }

    public void handleCheckChats(){

    }

    public void handleStartChat(){

    }

    public void handleConfiguration(){

    }

}
