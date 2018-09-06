package controllers;

import dao.DAOLists;
import dao.DAOStudent;
import containers.Assignment;
import views.View;
import containers.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentController extends Controller {

    View view;
    DAOLists daoLists;
    DAOStudent daoStudent;

    public StudentController(Model model, View view) {
        setMyModel(model);
        setloggedIn(true);
        this.view = view;
        this.daoLists = new DAOLists();
        this.daoStudent = new DAOStudent();
    }

    public void run() {

        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("Submit assignment");
        menuOptions.add("View grades");
        menuOptions.add("Take new assignment");
        menuOptions.add("Logout");

        while(getLoggedIn()) {
            View.printList();

            while(goodInput == false) {
                inputInt = view.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 5) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    view.showMessage("Only numbers from 1 to 4!");
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
        view.showAssignments(getMyModel().getAssignments());
        String assignmentID = view.takeStringInput("Which assignment would you like to submit? ");
        String url = view.takeStringInput("please, paste your url to this assignment ");
        getMyModel().getAssignments().get(assignmentID).setUrl(url);
        getMyModel().getAssignments().get(assignmentID).setIsFinished(true);
        daoStudent.add(getMyModel());
    }

    private void viewGrades() {
        view.showGrades(getMyModel().getAssignments());
    }

    private void takeNewAssignment() {
        view.printList(daoLists.getAllAssignments());
        String assignmentID = view.takeStringInput("Which assignment would you like to take? ");
        if(daoLists.getAllAssignments().contains(assignmentID) && !getMyModel().getAssignments().containsKey(assignmentID)) {
            getMyModel().getAssignments().put(assignmentID, new Assignment(assignmentID));
        }
        daoStudent.delete(getMyModel().getLogin());
        daoStudent.add(getMyModel().getLogin());
    }
}
