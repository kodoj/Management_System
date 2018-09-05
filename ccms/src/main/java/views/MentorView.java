package views;

import DAO.Assignment;
import DAO.Model;

import java.util.HashMap;
import java.util.List;

public class MentorView extends View {

    public void printStudentAssignments(HashMap<String, Assignment> assignments) {
        for (String name: assignments.keySet()){

            String key = name;
            String value = assignments.get(name).toString();
            showMessage(key + " " + value);
        }
    }

}
