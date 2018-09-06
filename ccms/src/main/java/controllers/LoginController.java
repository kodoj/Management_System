package controllers;

import dao.DAOLoginController;
import views.View;
import containers.Model;

public class LoginController {
    private String name;
    private String surname;
    private String password;
    private View view;
    private DAOLoginController daoLoginController;

    public LoginController(View view){
        this.view = view;
    }


    public Model getUserModel(){
        if(validatePassword()){
            return daoLoginController.createModel();
        }else{
            return null;
        }
    }


    private Boolean validatePassword(){
        name = view.takeStringInput("Name: ");
        surname = view.takeStringInput("Surname: ");
        password = view.takeStringInput("Password: ");
        daoLoginController = new DAOLoginController(name, surname, password);
        return daoLoginController.checkIfExist();
    }
}
