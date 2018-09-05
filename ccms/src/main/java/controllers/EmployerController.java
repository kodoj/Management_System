package controllers;

import DAO.DAOLists;
import DAO.Model;
import views.EmployerView;

public class EmployerController extends Controller {


    View view;
    DAOLists daoLists;

    public EmployerController(Model model, EmployerView employerView) {
        setMyModel(model);
        setloggedIn(true);
        this.view = view;
        this.daoLists = new DAOLists();
    }


    public void run(boolean getLoggedIn()) {

        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = {"List all students", "Logout"};

        while(getLoggedIn()) {
            view.printList(menuOptions);

            while(goodInput == false) {
                inputInt = view.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 3) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    view.showMessage("Only numbers from 1 to 2!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                view.printDAOList("students");
                continue;
            }
            else if(inputInt == 2) {
                setloggedIn(false);
                break;
            }

        }
    }
}