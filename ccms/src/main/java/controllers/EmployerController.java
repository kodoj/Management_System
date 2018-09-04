package controllers;

import DAO.DAOMassModel;
import DAO.Model;
import views.EmployerView;

public class EmployerController extends Controller {


    EmployerView employerView;
    DAOMassModel daoMassModel;

    public EmployerController(Model model, EmployerView employerView) {
        setMyModel(model);
        setloggedIn(true);
        this.employerView = employerView;
        this.daoMassModel = new DAOMassModel();
    }


    public void run(boolean getLoggedIn()) {

        int inputInt = 0;
        boolean goodInput = false;

        while(getLoggedIn()) {
            employerView.printMenu();

            while(goodInput == false) {
                inputInt = employerView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 3) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    System.out.println("Only numbers from 1 to 2!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                printStudents();
                continue;
            }
            else if(inputInt == 2) {
                setloggedIn(false);
                break;
            }

        }
    }

    private void printStudents() {
        employerView.printStudents(daoMassModel.getAllStudents());
        employerView.takeInput("Press anything to continue");
    }
}
