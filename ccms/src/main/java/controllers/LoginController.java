package controllers;

import dao.DAOLoginController;
import views.View;
import containers.Model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class LoginController {
    private String login;
    private String inputPassword;
    private View view;
    private DAOLoginController daoLoginController;
    private PasswordController passwordController;

    public LoginController(View view){
        this.view = view;
        this.passwordController = new PasswordController();
        daoLoginController = new DAOLoginController();
    }


    public Model getUserModel() throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException{
        if(validatePassword()){
            Model newUser = daoLoginController.createModel(this.login);
            System.out.println(newUser.getAccountType());
            return newUser;
        }else{
            return null;
        }
    }


    private Boolean validatePassword() throws IllegalArgumentException, NoSuchAlgorithmException, InvalidKeySpecException {
        login = view.takeStringInput("Login:");
        inputPassword = view.takeStringInput("Password: ");
        String hashedPassword = daoLoginController.getHashedPassword(this.login);
        return passwordController.check(inputPassword, hashedPassword);
    }


}
