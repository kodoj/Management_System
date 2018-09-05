package views;

import java.util.Scanner;

public abstract class View {

    public Scanner sc = new Scanner(System.in);


    public String takeStringInput(String string) {
        showMessage(string);
        String input = sc.nextLine();
        return input;
    }

    public int takeIntInput(String string) {
        showMessage(string);
        int input = sc.nextInt();
        return input;
    }

    public void printList(List<T> listElems){
        for(i=0; i<listElems.size(); i++){
            printMessage(i + ". " + listElems.get(i).toString());
        }
    }

    public void showMessage(String message){
        System.out.println(message);
    }

}
