package controllers;

import dao.DAOAssignment;
import dao.DAOLists;
import dao.DAOStudent;
import views.View;
import containers.Assignment;
import containers.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MentorController extends Controller {

    Model newModel;
    View view;
    DAOStudent daoStudent;
    DAOLists daoLists;
    DAOAssignment daoAssignment;

    public MentorController(Model model, View view) {
        this.view = view;
        setMyModel(model);
        this.setloggedIn(true);
        this.daoStudent = new DAOStudent();
        this.daoLists = new DAOLists();
        this.daoAssignment = new DAOAssignment();
    }


    public void run() {

        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("List all students");
        menuOptions.add("Add student");
        menuOptions.add("Remove student");
        menuOptions.add("Edit student");
        menuOptions.add("Get Assignments");
        menuOptions.add("Add new Assignment");
        menuOptions.add("Evaluate Assignments");
        menuOptions.add("Logout");

        while(getLoggedIn()) {
            View.printList(menuOptions);

            while(!goodInput) {
                int FIRST_INPUT = 0;
                int LAST_INPUT = 9;
                try{
                    inputInt = view.takeIntInput("What would you like to do? ");
                }catch(NumberFormatException e){
                    System.out.println("Wrong input!");
                }

                if(inputInt > FIRST_INPUT && inputInt < LAST_INPUT) {
                    goodInput = true;
                }else {
                    view.showMessage("Only numbers from 1 to 8!");
                }
            }
            goodInput = false;

            switch(inputInt){
                case 1:
                    View.printList(daoLists.getAllStudents());
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    editStudent();
                    break;
                case 5:
                    View.printList(daoLists.getAllAssignments());
                    break;
                case 6:
                    setNewAssignment();
                    break;
                case 7:
                    evaluateAssignment();
                    break;
                case 8:
                    break;
            }


        }
    }

    private void addStudent() {
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        String accountType = "student";
        String tempPassword = view.takeStringInput("Password ");
        String tempLogin = view.takeStringInput("Login ");
        Map<String, Assignment> assignments = new HashMap<String, Assignment>();

        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin, assignments);
        daoStudent.add(newModel);
    }

    private void removeStudent(){
        View.printList(daoLists.getAllStudents());
        String tempLogin = view.takeStringInput("Choose student: ");
        try{
            daoStudent.delete(tempLogin);
        }catch(NullPointerException e){
            System.out.println("Couldn't find student with given name");
        }

    }

    private void editStudent() {
        View.printList(daoLists.getAllStudents());
        String tempLogin = view.takeStringInput("Choose Student: ");
        boolean isFound = true;
        try{
            daoStudent.delete(tempLogin);
        }catch(NullPointerException e){
            System.out.println("Couldn't find student with given name");
            isFound = false;
        }
        if(isFound){
            addStudent();
        }else{
            System.out.println("Choose another student!");
        }

    }

    private void setNewAssignment() {
        String tempAssignmentName = view.takeStringInput("Please, enter the the assignment name ");
        daoAssignment.addAssignment(tempAssignmentName);
    }

    private void evaluateAssignment() {
        view.printList(daoLists.getAllStudents());
        String tempLogin = view.takeStringInput("Login ");
        newModel = daoStudent.get(tempLogin);
        view.showAssignments(newModel.getAssignments());
        String tempAssignmentName = view.takeStringInput("Which assignment would you like to grade? ");

        if(newModel.getAssignments().containsKey(tempAssignmentName)) {
            int grade = view.takeIntInput("What grade for this assignment? ");
            newModel.getAssignments().get(tempAssignmentName).setGrade(grade);
        }
        daoStudent.delete(tempLogin);
        daoStudent.add(newModel);
    }


}
