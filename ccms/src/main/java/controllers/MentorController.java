package controllers;

import DAO.Assignment;
import DAO.DAOEmployer;
import views.MentorView;
import DAO.Model;

import java.util.HashMap;
import java.util.Map;

public class MentorController extends Controller {

    Model newModel;
    Model myModel;
    MentorView mentorView;
    DAOEmployer daoEmployer;

    public MentorController(Model model, MentorView mentorView) {
        this.mentorView = mentorView;
        this.myModel = model;
        this.setloggedIn(true);
        this.daoEmployer = new DAOEmployer();
    }


    public void run(boolean getLoggedIn()) {

        String input;
        int inputInt = 0;
        boolean goodInput = false;

        while(getLoggedIn()) {
            mentorView.printMenu();

            while(goodInput == false) {
                inputInt = mentorView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 9) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    System.out.println("Only numbers from 1 to 6!");
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
        mentorView.printStudents(myModel.getAllStudents());
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
        daoEmployer.add(newModel);
    }

    private void removeStudent() {
        mentorView.printStudents(myModel.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);
    }

    private void editStudent() {
        mentorView.printStudents(myModel.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addStudent();
    }

    private void getAssignments() {
        mentorView.printAssignments(myModel.getAllAssignments());
    }

    private void setNewAssignment() {
        String tempAssignmentName = mentorView.takeInput("Please, enter the the assignment name ");
        myModel.putNewAssignment(tempAssignmentName);
    }

    private void evaluateAssignment() {
        mentorView.printStudents(myModel.getAllStudents());
        String tempName = mentorView.takeInput("Name ");
        String tempSurname = mentorView.takeInput("Surname ");
        newModel = daoEmployer.get(tempName, tempSurname);                // METODA GET? NIE WYOBRAŻAM SOBIE INNEGO EDYTOWANIA POJEDYNCZEGO POLA
        daoEmployer.delete(tempName, tempSurname);
        mentorView.printStudentAssignments(newModel.getAssignments());
        String tempAssignmentName = mentorView.takeInput("Which assignment would you like to grade? ");

        if(newModel.getAssignments().containsKey(tempAssignmentName)) {
            int grade = mentorView.takeIntInput("What grade for this assignment? ");
            newModel.getAssignments().get(tempAssignmentName).setGrade(grade);
        }
    }

}
