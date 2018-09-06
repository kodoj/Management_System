package controllers;

import dao.DAOLoginController;
import views.View;
import containers.Model;

public class LoginController {
    private String login;
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
        login = view.takeStringInput("Login:");
        password = view.takeStringInput("Password: ");
        daoLoginController = new DAOLoginController();
        daoLoginController.setLogin(login);
        daoLoginController.setPassword(password);
        return daoLoginController.checkIfExist();
    }
}
