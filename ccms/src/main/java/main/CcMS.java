package main;

import controllers.*;
import views.View;
import containers.Model;

import java.util.ArrayList;
import java.util.List;

public class CcMS {
    private Model user;
    private View view;
    private LoginController loginController;
    private boolean isRun;
    private String[] menu = {"1. Login", "2. Exit"};
    private Controller newController;

    public CcMS(){
        this.isRun = true;
        this.view = new View();
        this.loginController = new LoginController(this.view);
    }

    public void run() {
        setIsRun();
        while(isRun) {

            getModel();
            initializeNewUser();
            runNewUser();
            resetUser();
            setIsRun();
        }
    }
    private void setIsRun(){
        view.printArray(this.menu);
        int input = view.takeIntInput("Option: ");
        if(input == 1)
            this.isRun = true;
        else
            this.isRun = false;
    }

    public void getModel(){
            this.user = this.loginController.getUserModel();
    }

    private void initializeNewUser(){
        if(isAccountType("employee"))
            this.newController = new EmployerController(this.user, new View());
        else if (isAccountType("student"))
            this.newController = new StudentController(this.user, new View());
        else if(isAccountType("mentor"))
            this.newController = new MentorController(this.user, new View());
        else if(isAccountType("administrator"))
            this.newController = new AdministratorController(this.user, new View());
    }

    private boolean isAccountType(String role){
        boolean isAccountType = false;
        if(this.user != null)
            if(this.user.getAccountType().toLowerCase().equals(role))
                isAccountType = true;
        return  isAccountType;
    }

    private void runNewUser(){
        if(this.newController != null)
            newController.run();
    }

    private void resetUser(){
        this.user = null;
    }

}

