package infrastructure.input.CLI.pages;

import application.ports.InputPort;
import application.usecases.user.CreateUserUseCase;
import application.usecases.user.LogInUseCase;
import domain.entities.User;
import domain.exceptions.DomainException;
import infrastructure.input.CLI.ConsoleRunner;
import infrastructure.utils.MessageProvider;

public class WelcomePage {

    private final InputPort input;
    private final MessageProvider messageProvider;
    private final LogInUseCase logInUseCase;
    private final CreateUserUseCase createUserUseCase;

    public static final int LOGIN = 1;
    public static final int CREATE_ACCOUNT = 2;
    public static final int EXIT = 0;

    public WelcomePage(InputPort input , MessageProvider messageProvider , CreateUserUseCase createUserUseCase , LogInUseCase logInUseCase){
        this.input = input;
        this.messageProvider = messageProvider;
        this.logInUseCase = logInUseCase;
        this.createUserUseCase = createUserUseCase;
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
            User user = logInUseCase.execute(username , password);
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
            User user = createUserUseCase.execute(username , password);
            this.runner.login(user);
        } catch (DomainException e){
            System.out.println(messageProvider.getError(e));
        }
    }
}
