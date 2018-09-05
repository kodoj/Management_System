package views;

import containers.Assignment;
import containers.Model;

import java.util.HashMap;
import java.util.List;

public class MentorView extends View {


    @Override
    public void printMenu() {
        System.out.println("1. List all students");
        System.out.println("2. Add student");
        System.out.println("3. Remove student");
        System.out.println("4. Edit student");
        System.out.println("5. Get Assignments");
        System.out.println("6. Add new Assignment");
        System.out.println("7. Evaluate Assignments");
        System.out.println("8. Logout");
    }


    public void printStudents(List<Model> models) {
        for(int i = 0; i < models.size(); i++) {
            System.out.println(models.get(i).toString());
        }
    }

    public void printAssignments(List<Assignment> assignments) {
        for(int i = 0; i < assignments.size(); i++) {
            System.out.println(assignments.get(i).toString());
        }

    }

    public void printStudentAssignments(HashMap<String, Assignment> assignments) {
        for (String name: assignments.keySet()){

            String key = name;
            String value = assignments.get(name).toString();
            System.out.println(key + " " + value);
        }
    }

}
