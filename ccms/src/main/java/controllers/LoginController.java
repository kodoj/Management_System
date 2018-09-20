package controllers;

import dao.DAOLoginController;
import maskingThread.CatchPassword;
import views.View;
import containers.Model;

public class LoginController {
    private String login;
    private String password;
    private View view;
    private DAOLoginController daoLoginController;
    private CatchPassword catchPassword;

    public LoginController(View view){
        this.view = view;
    }


    public Model getUserModel(){
        if(validatePassword()){
            Model newUser = daoLoginController.createModel();
            System.out.println(newUser.getAccountType());
            return newUser;
        }else{
            return null;
        }
    }


    private Boolean validatePassword(){
        catchPassword = new CatchPassword();
        login = view.takeStringInput("Login:");
        password = catchPassword.catchPasswordToHide();
        daoLoginController = new DAOLoginController();
        daoLoginController.setLogin(login);
        daoLoginController.setPassword(password);
        return daoLoginController.checkIfExist();
    }
}
