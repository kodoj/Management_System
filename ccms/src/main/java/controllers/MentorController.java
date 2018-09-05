package controllers;

import DAO.DAOLists;
import DAO.DAOStudent;
import containers.Assignment;
import containers.Model;
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

        while(getLoggedIn()) {
            mentorView.printMenu();

            while(goodInput == false) {
                inputInt = mentorView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 9) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    System.out.println("Only numbers from 1 to 8!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                printStudents();
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
                getAssignments();
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

    private void printStudents() {
        mentorView.printStudents(daoLists.getAllStudents());
        mentorView.takeInput("Press anything to continue");
    }

    private void addStudent() {
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        String accountType = "student";
        String tempPassword = mentorView.takeInput("Password");
        String tempLogin = mentorView.takeInput("Login");
        Map<String, Assignment> assignments = new HashMap<String, Assignment>();
        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin, assignments);
        daoStudent.add(newModel);
    }

    private void removeStudent() {
        mentorView.printStudents(daoLists.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoStudent.delete(tempName, tempSurname);
    }

    private void editStudent() {
        mentorView.printStudents(daoLists.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoStudent.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addStudent();
    }

    private void getAssignments() {
        mentorView.printAssignments(daoLists.getAllAssignments());
    }

    private void setNewAssignment() {
        String tempAssignmentName = mentorView.takeInput("Please, enter the the assignment name ");
        daoLists.setNewAssignment(tempAssignmentName);
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
