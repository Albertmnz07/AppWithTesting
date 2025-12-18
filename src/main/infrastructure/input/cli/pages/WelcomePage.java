package main.infrastructure.input.cli.pages;

import main.domain.entities.User;
import main.infrastructure.input.cli.ConsoleRunner;
import main.infrastructure.input.cli.utils.InputReader;

public class WelcomePage {

    ConsoleRunner runner;

    public static final int LOGIN = 1;
    public static final int CREATE_ACCOUNT = 2;
    public static final int EXIT = 0;

    public WelcomePage(ConsoleRunner runner){
        this.runner = runner;
    }

    public void show(){
        System.out.println("Welcome to chat");
        System.out.println("Please, choose an option");
        System.out.println("1.Log In\n2.Create Account\n0.Exit");
        int selection = InputReader.readInt("Option: ");

        switch (selection){
            case LOGIN -> handleLogIn();
            case EXIT -> runner.exit();
        }
    }

    public void  handleLogIn(){
        System.out.println("LOG IN");
        String username = InputReader.readString("Username");
        String password = InputReader.readString("Password");

        try{
            User user = runner.getLogInUseCase().execute(username , password);
            System.out.println("Login successful");
            this.runner.login(user);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public void handleCreateAccount(){
        System.out.println("Create Account");
        String username = InputReader.readString("Username");
        String password = InputReader.readString("Password");

        try{
            User user = runner.getCreateUserUseCase().execute(username , password);
            this.runner.login(user);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
