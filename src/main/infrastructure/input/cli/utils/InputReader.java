package main.infrastructure.input.cli.utils;

import main.application.ports.InputPort;

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

    public static void changeInputStream(java.io.InputStream in) {
        sc = new Scanner(in);
    }
}
