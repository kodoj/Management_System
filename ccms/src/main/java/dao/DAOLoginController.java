package dao;

import connectors.Connector;
import containers.Assignment;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import containers.Model;

import java.util.HashMap;


public class DAOLoginController {
    private String login;
    private String password;
    private Connector connector;
    private Element elementPerson;


    public DAOLoginController(){
        this.connector = new Connector();
    }

    public void setLogin(String login){
        this.login = login;
    }


    public void setPassword(String password){
        this.password = password;
    }


    public Model createModel(String login){
        elementPerson = connector.loadPerson(login);
        Element accountTypeElement = (Element) elementPerson.getElementsByTagName("accounttype").item(0);
        String accountType = accountTypeElement.getTextContent();
        if(accountType.equals("student")){
            DAOStudent daoStudent = new DAOStudent();
            return daoStudent.get(login);
        }else if(accountType.equals("mentor") || accountType.equals("employee") || accountType.equals("administrator")){
            DAOEmployer daoEmployer = new DAOEmployer();
            return daoEmployer.get(login);
        }
        return null;
    }


    public Boolean checkIfExist(){
        Element element = connector.loadPerson(login);
        if (element == null) {
            return false;
        }
        Element expected = (Element) element.getElementsByTagName("password").item(0);
        String xmlpassword = expected.getTextContent();
        return xmlpassword.equals(password);
    }

    public String getHashedPassword(String login){
        Element element = connector.loadPerson(login);
        Element hashedPassword = (Element) element.getElementsByTagName("password").item(0);
        return hashedPassword.getTextContent();
    }


}

