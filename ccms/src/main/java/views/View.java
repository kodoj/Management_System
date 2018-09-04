package views;

import java.util.Scanner;

public abstract class View {

    public Scanner sc = new Scanner(System.in);

    abstract void printMenu();

    abstract String takeInput();

    public void logout() {

    }
}
