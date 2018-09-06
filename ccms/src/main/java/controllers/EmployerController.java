package controllers;

import dao.DAOLists;
import containers.Model;
import views.View;

import java.util.ArrayList;
import java.util.List;

public class EmployerController extends Controller {


    View view;
    DAOLists daoLists;

    public EmployerController(Model model, View view) {
        setMyModel(model);
        setloggedIn(true);
        this.view = view;
        this.daoLists = new DAOLists();
    }


    public void run() {

        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = new ArrayList<String>();
        menuOptions.add("List all students");
        menuOptions.add("Logout");

        while(getLoggedIn()) {
            View.printList(menuOptions);

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
                View.printList(daoLists.getAllStudents());
                continue;
            }
            else if(inputInt == 2) {
                setloggedIn(false);
                break;
            }

        }
    }
}
