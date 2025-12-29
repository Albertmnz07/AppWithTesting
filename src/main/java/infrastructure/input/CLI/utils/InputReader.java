package infrastructure.input.CLI.utils;

import application.ports.InputPort;

import java.util.Scanner;

public class InputReader implements InputPort {

    private static Scanner sc = new Scanner(System.in);

    public String readString(String prompt){
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public int readInt(String prompt){
        while (true){
            System.out.print(prompt + ": ");
            String input = sc.nextLine();

            try{
                return Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.out.println("Please, enter a number");
            }
        }
    }
}
