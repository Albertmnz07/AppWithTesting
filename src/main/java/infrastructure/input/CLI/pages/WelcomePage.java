package infrastructure.input.CLI.pages;

import application.ports.InputPort;
import domain.entities.User;
import domain.exceptions.DomainException;
import infrastructure.input.CLI.ConsoleRunner;
import infrastructure.utils.MessageProvider;

public class WelcomePage {

    private final ConsoleRunner runner;
    private final InputPort input;
    private final MessageProvider messageProvider;

    public static final int LOGIN = 1;
    public static final int CREATE_ACCOUNT = 2;
    public static final int EXIT = 0;

    public WelcomePage(ConsoleRunner runner , InputPort input , MessageProvider messageProvider){
        this.runner = runner;
        this.input = input;
        this.messageProvider = messageProvider;
    }

    public void show(ConsoleRunner runner){
        System.out.println("Welcome to chat");
        System.out.println("Please, choose an option");
        System.out.println("1.Log In\n2.Create Account\n0.Exit");
        int selection = input.readInt("Option");

        switch (selection){
            case LOGIN -> handleLogIn();
            case CREATE_ACCOUNT -> handleCreateAccount();
            case EXIT -> runner.exit();
        }
    }

    public void  handleLogIn(){
        System.out.println("LOG IN");
        String username = input.readString("Username");
        String password = input.readString("Password");

        try{
            User user = runner.getLogInUseCase().execute(username , password);
            System.out.println("Login successful");
            this.runner.login(user);
        } catch (DomainException e){
            System.out.println(messageProvider.getError(e));
        }
    }

    public void handleCreateAccount(){
        System.out.println("Create Account");
        String username = input.readString("Username");
        String password = input.readString("Password");

        try{
            User user = runner.getCreateUserUseCase().execute(username , password);
            this.runner.login(user);
        } catch (DomainException e){
            System.out.println(messageProvider.getError(e));
        }
    }
}
