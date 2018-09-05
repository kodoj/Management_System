package controllers;

import DAO.DAOEmployer;
import DAO.Model;
import views.view;
import DAO.DAOMassModel;

public class AdministratorController extends Controller {

    View view;
    DAOMassModel daoMassModel;
    DAOEmployer daoEmployer;
    Model newModel;

    public MentorController(Model model, View view) {
        setMyModel(model);
        setloggedIn(true);
        this.view = view;
        this.daoMassModel = new DAOMassModel();
        this.daoEmployer = new DAOEmployer();

    }


    public void run(boolean getLoggedIn()) {

        String input;
        int inputInt = 0;
        boolean goodInput = false;
        List<String> menuOptions = {"List all students","List all mentors","Add mentor", "Remove mentor", "Edit mentor", "Logout"};


        while(getLoggedIn()) {
            view.printList();

            while(goodInput == false) {
                inputInt = view.takeIntInput("What would you like to do? ");
                if(inputInt > 0 && inputInt < 7) {                               // magic number, to improve!
                    goodInput = true;
                } else {
                    view.showMessage("Only numbers from 1 to 6!");
                }
            }
            goodInput = false;

            if(inputInt == 1) {
                view.printMassModelList("students");
                continue;
            }
            else if(inputInt == 2) {
                view.printMassModelList("mentors");
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

    private void addMentor() {
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        String accountType = "mentor";
        String tempPassword = view.takeStringInput("Password");
        String tempLogin = view.takeStringInput("Login");
        newModel = new Model(tempName, tempSurname, accountType, tempPassword, tempLogin);
        daoEmployer.add(newModel);
    }

    private void removeMentor() {
        view.printList(daoMassModel.getAllMentors());
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);
    }

    private void editMentor() {
        view.printList(daoMassModel.getAllMentors());
        String tempName = view.takeStringInput("Name ");
        String tempSurname = view.takeStringInput("Surname ");
        daoEmployer.delete(tempName, tempSurname);                             // USUWANIE PO IMIENIU I NAZWISKU?
        addMentor();
    }
}
