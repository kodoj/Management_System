package main;

import controllers.*;
import views.AdministratorView;
import views.EmployerView;
import views.MentorView;
import views.StudentView;

public class CcMS {
    private Model user;
    private View view;
    private LoginController loginController;

    public CcMS(){
        this.view = new View();
        this.loginController = new LoginController(this.view);
    }

    public void run() {
        //TO DO MENU working on while and input 1.login 2.exit
        getModel();
        initializeNewUser();
        resetUser();
    }

    public void getModel(){
        while (this.user == null) {
            this.user = getUserModel();
        }
    }

    private void initializeNewUser(){
        Controller newUser;
        if(this.user.getAccountType().toLowerCase().equals("employer"))
            newUser = new EmployerController(this.user, new EmployerView());
        else if(this.user.getAccountType().toLowerCase().equals("mentor"))
            newUser = new MentorController(this.user, new MentorView());
        else if(this.user.getAccountType().toLowerCase().equals("administrator"))
            newUser = new AdministratorController(this.user, new AdministratorView());
        else{
            newUser = new StudentController(this.user, new StudentView()); //guest account
        }
        newUser.run(true);
    }

    private void resetUser(){
        this.user = null;
    }

}

