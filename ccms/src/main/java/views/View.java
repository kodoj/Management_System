package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import dao.DAOLists;
import containers.Assignment;
import controllers.Controller;

public class View {

    DAOLists daoLists;
    Controller controller;
    private BufferedReader bufferedReader;

    public View(){
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String takeStringInput(String string) {
        showMessage(string);
        String input = "";
        try{
            input = bufferedReader.readLine();
        }catch(IOException e){
            System.out.println("Wrong input");
        }

        return input;
    }

    public Integer takeIntInput(String string) {
        showMessage(string);
        Integer input = null;
        try{
            input = Integer.parseInt(bufferedReader.readLine());
        }catch(IOException e){
            System.out.println("Wrong input");
        }

        return input;
    }

    public static <T> void printList(List<T> listElems){
        for(int i=0; i<listElems.size(); i++){
            T element = listElems.get(i);
            if(element instanceof  String)
                System.out.println((i + ". " + element));
            else
                System.out.println((i + ". " + element.toString()));
        }
    }



    public void printArray(String[] list){
        for(int i=0; i<list.length; i++)
            System.out.println(list[i]);
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void showAssignments(Map<String, Assignment> assignments){
        for (String name: assignments.keySet()){

            String key = name;
            
            String value = assignments.get(name).toString();
            
            showMessage(key + " " + value);
        }

    }

    public void showGrades(Map<String, Assignment> assignments){
        for (String name: assignments.keySet()){

            String key = name;
            
            Integer value = assignments.get(name).getGrade();
            
            showMessage(key + " " + value);
        }

    }
}
