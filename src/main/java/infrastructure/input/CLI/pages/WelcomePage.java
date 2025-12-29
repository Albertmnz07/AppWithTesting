package main.java.infrastructure.input.CLI.pages;

import main.java.application.ports.InputPort;
import main.java.domain.entities.User;
import main.java.domain.exceptions.DomainException;import main.java.infrastructure.input.CLI.ConsoleRunner;
import main.java.infrastructure.input.CLI.utils.CliErrorMessage;

public class WelcomePage {

    private final ConsoleRunner runner;
    private final InputPort input;

    public static final int LOGIN = 1;
    public static final int CREATE_ACCOUNT = 2;
    public static final int EXIT = 0;

    public WelcomePage(ConsoleRunner runner , InputPort input){
        this.runner = runner;
        this.input = input;
    }

    public void show(){
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
            System.out.println(CliErrorMessage.from(e.getCode()));
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
            System.out.println(CliErrorMessage.from(e.getCode()));
        }
    }
}
