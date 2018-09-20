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

            while(!goodInput) {
                int FIRST_INPUT = 0;
                int LAST_INPUT = 3;
                try{
                    inputInt = view.takeIntInput("What would you like to do? ");
                }catch(NumberFormatException e){
                    System.out.println("Wrong input!");
                }

                if(inputInt > FIRST_INPUT && inputInt < LAST_INPUT) {
                    goodInput = true;
                } else {
                    view.showMessage("Only numbers from 1 to 2!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                View.printList(daoLists.getAllStudents());
            }
            else if(inputInt == 2) {
                setloggedIn(false);

            }

        }
    }
}
