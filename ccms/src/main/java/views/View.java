package views;

import java.util.Scanner;

public abstract class View {

    public Scanner sc = new Scanner(System.in);


    public String takeInput(String string) {
        System.out.println(string);
        String input = sc.nextLine();
        return input;
    }

    abstract int printMenu();

}
