package controllers;

import DAO.Model;
import views.StudentView;

public class StudentController extends Controller {


    StudentView studentView;

    public StudentController(Model model, StudentView studentView) {
        setMyModel(model);
        setloggedIn(true);
        this.studentView = studentView;
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
            else if(inputInt == 8) {
                setloggedIn(false);
                break;
            }

        }
    }

    private void submitAssignment() {
        
    }
}
