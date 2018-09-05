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

    public void showMessage(String message){
        System.out.println(message);
    }

    public void printDAOList(String option) {

        if(option == "students"){
            printList(daoLists.getAllStudents());
        } else if (option == "mentors"){
            printList(daoLists.getAllMentors());
        } else if (option = "assignments") {
            printList(daoLists.getAllAssignments());
        }
        
        takeStringInput("Press anything to continue");
    }

    public void printHashMap(String option){
        HashMap<String, Assignment> assignments = controller.getMyModel().getAssignments();
        for (String name: assignments.keySet()){

            String key = name;
            
            if(option == "assignments") {
                String value = assignments.get(name).toString();
            } else if (option == "grades") {
                Integer value = assignments.get(name).getGrade();
            }
            
            showMessage(key + " " + value);
        }

    }
}
