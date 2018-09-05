package controllers;

import DAO.DAOEmployer;
import containers.Model;
import views.AdministratorView;
import DAO.DAOLists;

public class AdministratorController extends Controller {

    AdministratorView administratorView;
    DAOLists daoLists;
    DAOEmployer daoEmployer;
    Model newModel;

    public MentorController(Model model, AdministratorView administratorView) {
        setMyModel(model);
        setloggedIn(true);
        this.administratorView = administratorView;
        this.daoLists = new DAOLists();
        this.daoEmployer = new DAOEmployer();

    }


    public void run(boolean getLoggedIn()) {

        String input;
        int inputInt = 0;
        boolean goodInput = false;

        while(getLoggedIn()) {
            administratorView.printMenu();

            while(goodInput == false) {
                inputInt = administratorView.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 7) {                               // magic number, to improve!
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
                printMentors();
                continue;
            }
            else if(inputInt == 3) {
                addMentor();
                continue;
            }
            else if(inputInt == 4) {
                removeMentor();
                continue;
            }
            else if(inputInt == 5) {
                editMentor();
                continue;
            }
            else if(inputInt == 6) {
                setloggedIn(false);
                break;
            }

        }
    }

    private void printStudents() {
        administratorView.printAllModels(daoLists.getAllStudents());
        administratorView.takeInput("Press anything to continue");
    }


    private void printMentors() {
        administratorView.printAllModels(daoLists.getAllMentors());
        administratorView.takeInput("Press anything to continue");
    }

    private void addMentor() {
        String tempName = administratorView.takeInput("Name ");
        String tempSurname = administratorView.takeInput("Surname ");
        String accountType = "mentor";
        String tempPassword = administratorView.takeInput("Password");
        String tempLogin = administratorView.takeInput("Login");
        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin);
        daoEmployer.add(newModel);
    }

    private void removeMentor() {
        administratorView.printAllModels(daoLists.getAllMentors());
        String tempName = administratorView.takeInput("Name ");
        String tempSurname = administratorView.takeInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);
    }

    private void editMentor() {
        administratorView.printAllModels(daoLists.getAllMentors());
        String tempName = administratorView.takeInput("Name ");
        String tempSurname = administratorView.takeInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addMentor();
    }
}
