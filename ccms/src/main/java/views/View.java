package views;

import java.util.HashMap;
import java.util.Scanner;

import DAO.DAOLists;
import containers.Assignment;
import controllers.Controller;

public class View {

    DAOLists daoLists;
    Controller controller;
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
        for(int i=0; i<listElems.size(); i++){
            showMessage(i + ". " + listElems.get(i).toString());
        }
    }
    public void printArray(String[] list){
        for(int i=0; i<list.length; i++)
            System.out.println(list[i]);
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void showAssignments(HashMap<String, Assignment> assignments){
        for (String name: assignments.keySet()){

            String key = name;
            
            String value = assignments.get(name).toString();
            
            showMessage(key + " " + value);
        }

    }

    public void showGrades(HashMap<String, Assignment> assignments){
        for (String name: assignments.keySet()){

            String key = name;
            
            Integer value = assignments.get(name).getGrade();
            
            showMessage(key + " " + value);
        }

    }
}
