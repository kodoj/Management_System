package controllers;

import DAO.DAOLists;
import containers.Assignment;
import containers.Model;
import views.StudentView;

public class StudentController extends Controller {


    StudentView studentView;
    DAOLists daoLists;

    public StudentController(Model model, StudentView studentView) {
        setMyModel(model);
        setloggedIn(true);
        this.studentView = studentView;
        this.daoLists = new DAOLists();
    }

    public void run(boolean getLoggedIn()) {

        int inputInt = 0;
        boolean goodInput = false;

        while(getLoggedIn()) {
            studentView.printMenu();

            while(goodInput == false) {
                inputInt = studentView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 5) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    System.out.println("Only numbers from 1 to 4!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                submitAssignment();
                continue;
            }
            else if(inputInt == 2) {
                viewGrades();
                continue;
            }
            else if(inputInt == 3) {
                takeNewAssignment();
                continue;
            }
            else if(inputInt == 4) {
                setloggedIn(false);
                break;
            }

        }
    }

    private void submitAssignment() {
        studentView.printStudentAssignments(getMyModel().getAssignments());
        String assignmentID = studentView.takeInput("Which assignment would you like to submit? ");
        String url = studentView.takeInput("please, paste your url to this assignment ");
        getMyModel().getAssignments().get(assignmentID).setUrl(url);
        getMyModel().getAssignments().get(assignmentID).setIsFinished(true);
    }

    private void viewGrades() {
        studentView.printMyGrades(getMyModel().getAssignments());
    }

    private void takeNewAssignment() {
        studentView.printAllAssignments(daoLists.getAllAssignments());
        String assignmentID = studentView.takeInput("Which assignment would you like to take? ");
        if(daoLists.getAllAssignments().contains(assignmentID) && !getMyModel().getAssignments().containsKey(assignmentID)) {
            getMyModel().getAssignments().put(assignmentID, new Assignment(assignmentID));
        }
    }
}
