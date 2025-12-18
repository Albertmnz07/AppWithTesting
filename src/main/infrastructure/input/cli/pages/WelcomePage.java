package main.infrastructure.input.cli.pages;

import main.infrastructure.input.cli.ConsoleRunner;

public class WelcomePage {

    ConsoleRunner runner;

    public WelcomePage(ConsoleRunner runner){
        this.runner = runner;
    }

    public void show(){
        System.out.println("Welcome to chat");
        System.out.println("Please, choose an option");
        System.out.println("1.Log In\n2.Create Account\n0.Exit");
    }
}
