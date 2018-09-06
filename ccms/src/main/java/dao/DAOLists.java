package dao;

import connectors.Connector;
import containers.Assignment;
import containers.Model;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class DAOLists implements DAOMultipleObjects {

    private Connector connector;
    private List<Model> models;
    private List<Assignment> assignments;
    private Element element;
    private NodeList nodeList;
    private Assignment assignment;
    private String userType;

    private String tempLogin;
    private String tempName;
    private String tempSurname;
    private Element elementLogin;
    private Element elementName;
    private Element elementSurname;

    public DAOLists() {
        this.connector = new Connector();
        this.models =  new ArrayList<Model>();
        this.assignments = new ArrayList<Assignment>();
    }

    public List<Model> getAllStudents() {
        userType = "student";
        return getAllUsers(userType);
    }

    public List<Model> getAllMentors() {
        userType = "mentor";
        return getAllUsers(userType);
    }

    public List<Model> getAllEmployers() {
        userType = "employer";
        return getAllUsers(userType);
    }

    public List<Model> getAllUsers(String userType) {
        element = connector.loadListOfPersons(userType + "s");
        nodeList = element.getElementsByTagName(userType);

        for(int i = 0; i < nodeList.getLength(); i++) {
            elementLogin = (Element) element.getElementsByTagName("login").item(0);
            tempLogin = elementLogin.getTextContent();

            elementName = (Element) element.getElementsByTagName("name").item(0);
            tempName = elementName.getTextContent();

            elementSurname = (Element) element.getElementsByTagName("surname").item(0);
            tempSurname = elementSurname.getTextContent();
            models.add(new Model(tempName, tempSurname, tempLogin));
        }
        return models;

    }

    public List<Assignment> getAllAssignments() {
        element = connector.loadAssigments();
        nodeList = element.getElementsByTagName("assignment");
        for(int i = 0; i < nodeList.getLength(); i++) {
            assignment = new Assignment(nodeList.item(i).getAttributes().getNamedItem("assignment").getNodeValue());
            assignments.add(assignment);
        }
        return null;
    }
}
