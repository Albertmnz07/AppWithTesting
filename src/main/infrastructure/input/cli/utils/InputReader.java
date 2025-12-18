package main.infrastructure.input.cli.utils;

import java.util.Scanner;

public class InputReader {

    private static final Scanner sc = new Scanner(System.in);

    public static String readString(String prompt){
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public static int readInt(String prompt){
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
