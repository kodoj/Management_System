package dao;

import connectors.Connector;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class DAOLoginController {
    private String login;
    private String password;
    private Connector connector;
    private Element elementPerson;
    private NodeList nodePersonList;

    public static void main(String[] args) {
        DAOLoginController daoLoginController = new DAOLoginController();

    }
    DAOLoginController(){
        this.connector = new Connector();
        setLogin("xxxpenetratorxxx");
        setPassword("admin123");
        getPersonModel();
    }

    public void setLogin(String login){
        this.login = login;
    }


    public void setPassword(String password){
        this.password = password;
    }


    public void getPersonModel(){
        if(connector.loadPerson(login)!= null){
            elementPerson = connector.loadPerson(login);
            String documentElement = elementPerson.getLocalName();
            System.out.println(documentElement);
          //  nodePersonList = elementPerson.getElementsByTagName()
        }
     //   return null;
    }



}
