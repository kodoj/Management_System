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


    public Model createModel(){
        if(checkIfExist()){
            elementPerson = connector.loadPerson(login);
            String accountType = elementPerson.getElementsByTagName("accounttype").item(0).getTextContent();
            if(accountType.equals("student")){
                DAOStudent daoStudent = new DAOStudent();
                return daoStudent.get(login);
            }else if(accountType.equals("mentor") || accountType.equals("employee")){
                DAOEmployer daoEmployer = new DAOEmployer();
                return daoEmployer.get(login);
            }
        }
        return null;
    }


    public Boolean checkIfExist(){
        Element element = connector.loadPerson(login);
        return element != null && (element.getElementsByTagName("password").equals(password));
    }


}


/*
            String name;
            String surname;
            Model model;
            elementPerson = connector.loadPerson(login);
            String accountType = elementPerson.getLocalName();

            if(accountType.equals("student")){
                name = elementPerson.getElementsByTagName("name").item(0).getTextContent();
                System.out.println("ElementPerson :" + name); //testowe printy
                surname = elementPerson.getElementsByTagName("surname").item(0).getTextContent();
                System.out.println("ElementPerson :" + surname);
                HashMap<String, Assignment> personHashMap = new HashMap<String, Assignment>();
                NodeList personList = elementPerson.getElementsByTagName("assignment");

                for(int i = 0; i < personList.getLength(); i ++) {
                    Node evalNode = personList.item(0);
                    Element evalElement = (Element) evalNode;
                    name = evalElement.getElementsByTagName("name").item(0).getTextContent();
                }

            }else if(accountType.equals("mentor") || accountType.equals("employee")){
                name = elementPerson.getElementsByTagName("name").item(0).getTextContent();
                System.out.println("ElementPerson name: " + name); //testowe printy
                surname = elementPerson.getElementsByTagName("surname").item(0).getTextContent();
                System.out.println("ElementPerson surname " + surname);

                model = new Model(name, surname,accountType,password, login);
                return model;
            }
 */
