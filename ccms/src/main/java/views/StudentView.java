package views;


import containers.Assignment;

import java.util.HashMap;
import java.util.List;

public class StudentView extends View {

    @Override
    public void printMenu() {
        System.out.println("1. Submit assignment");
        System.out.println("2. View grades");
        System.out.println("3. Take new assignment");
        System.out.println("4. Logout");
    }


    public void printStudentAssignments(HashMap<String, Assignment> assignments) {
        for (String name: assignments.keySet()){

            String key = name;
            String value = assignments.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

    public void printMyGrades(HashMap<String, Assignment> assignments) {
        for (String name: assignments.keySet()){

            String key = name;
            Integer value = assignments.get(name).getGrade();
            System.out.println(key + " " + value);
        }
    }

    public void printAllAssignments(List<Assignment> assignments) {
        for(int i = 0; i < assignments.size(); i++) {
            System.out.println(assignments.get(i).toString());
        }

    }

}
