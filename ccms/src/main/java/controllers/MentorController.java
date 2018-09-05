package controllers;

import DAO.DAOLists;
import DAO.DAOStudent;
import views.View;
import containers.Assignment;
import containers.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MentorController extends Controller {

    Model newModel;
    View view;
    DAOStudent daoStudent;
    DAOLists daoLists;

    public MentorController(Model model, View view) {
        this.view = view;
        setMyModel(model);
        this.setloggedIn(true);
        this.daoStudent = new DAOStudent();
        this.daoLists = new DAOLists();
    }


    public void run(boolean getLoggedIn()) {

        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = {"List all students","Add student","Remove student","Edit student", "Get Assignments", "Add new Assignment", "Evaluate Assignments","Logout"};

        while(getLoggedIn()) {
            view.printList(menuOptions);

            while(goodInput == false) {
                inputInt = view.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 9) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    view.showMessage("Only numbers from 1 to 8!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                view.printDAOList("students");
                continue;
            }
            else if(inputInt == 2) {
                addStudent();
                continue;
            }
            else if(inputInt == 3) {
                removeStudent();
                continue;
            }
            else if(inputInt == 4) {
                editStudent();
                continue;
            }
            else if(inputInt == 5) {
                view.printDAOList("assignments");
                continue;
            }
            else if(inputInt == 6) {
                setNewAssignment();
                continue;
            }
            else if(inputInt == 7) {
                evaluateAssignment();
                continue;
            }
            else if(inputInt == 8) {
                setloggedIn(false);
                break;
            }

        }
    }

    private void addStudent() {
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        String accountType = "student";
        String tempPassword = view.takeStringInput("Password");
        String tempLogin = view.takeStringInput("Login");
        Map<String, Assignment> assignments = new HashMap<String, Assignment>();

        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin, assignments);
        daoStudent.add(newModel);
    }

    private void removeStudent() {
        view.printDAOList("students");
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        daoStudent.delete(tempName, tempSurname);
    }

    private void editStudent() {
        view.printDAOList("students");
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        daoStudent.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addStudent();
    }

    private void setNewAssignment() {
        String tempAssignmentName = view.takeStringInput("Please, enter the the assignment name ");
        daoLists.setNewAssignment(tempAssignmentName);
    }

    private void evaluateAssignment() {
        view.printList("students");
        String tempName = view.takeInput("Name ");
        String tempSurname = view.takeInput("Surname ");
        newModel = daoStudent.get(tempName, tempSurname);                // METODA GET? NIE WYOBRAŻAM SOBIE INNEGO EDYTOWANIA POJEDYNCZEGO POLA
        daoStudent.delete(tempName, tempSurname);
        view.printHashMap("assignments");
        String tempAssignmentName = view.takeStringInput("Which assignment would you like to grade? ");

        if(newModel.getAssignments().containsKey(tempAssignmentName)) {
            int grade = view.takeIntInput("What grade for this assignment? ");
            newModel.getAssignments().get(tempAssignmentName).setGrade(grade);
        }
    }

}
