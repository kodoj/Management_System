package controllers;

import DAO.*;
import views.MentorView;

import java.util.HashMap;
import java.util.Map;

public class MentorController extends Controller {

    Model newModel;
    MentorView mentorView;
    DAOStudent daoStudent;
    DAOLists daoLists;

    public MentorController(Model model, MentorView mentorView) {
        this.mentorView = mentorView;
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
            mentorView.printList(menuOptions);

            while(goodInput == false) {
                inputInt = mentorView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 9) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    mentorView.showMessage("Only numbers from 1 to 8!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                mentorView.printDAOList("students");
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
                mentorView.printDAOList("assignments");
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
        String tempName = mentorView.takeStringInput("Name ");
        String tempSurname = mentorView.takeStringInput("Surname ");
        String accountType = "student";
        String tempPassword = mentorView.takeStringInput("Password");
        String tempLogin = mentorView.takeStringInput("Login");
        Map<String, Assignment> assignments = new HashMap<String, Assignment>();

        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin, assignments);
        daoStudent.add(newModel);
    }

    private void removeStudent() {
        mentorView.printList(daoLists.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoStudent.delete(tempName, tempSurname);
    }

    private void editStudent() {
        mentorView.printDAOList("students");
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoStudent.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addStudent();
    }

    private void setNewAssignment() {
        String tempAssignmentName = mentorView.takeInput("Please, enter the the assignment name ");
        myModel.setNewAssignment(tempAssignmentName);
    }

    private void evaluateAssignment() {
        mentorView.printStudents(daoLists.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        newModel = daoStudent.get(tempName, tempSurname);                // METODA GET? NIE WYOBRAŻAM SOBIE INNEGO EDYTOWANIA POJEDYNCZEGO POLA
        daoStudent.delete(tempName, tempSurname);
        mentorView.printStudentAssignments(getMyModel().getAssignments());
        String tempAssignmentName = mentorView.takeInput("Which assignment would you like to grade? ");

        if(newModel.getAssignments().containsKey(tempAssignmentName)) {
            int grade = mentorView.takeIntInput("What grade for this assignment? ");
            newModel.getAssignments().get(tempAssignmentName).setGrade(grade);
        }
    }

}
