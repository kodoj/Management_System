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
        nodeList = connector.loadListOfPersons(userType);
        models.clear();
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element nextElement = (Element) nodeList.item(i);
            tempLogin = nextElement.getAttribute("login");

            elementName = (Element) nextElement.getElementsByTagName("name").item(0);
            tempName = elementName.getTextContent();

            elementSurname = (Element) nextElement.getElementsByTagName("surname").item(0);
            tempSurname = elementSurname.getTextContent();
            models.add(new Model(tempName, tempSurname, tempLogin));
        }
        return models;

    }

    public List<Assignment> getAllAssignments() {
        nodeList = connector.loadAssigments();
        assignments.clear();
        for(int i = 0; i < nodeList.getLength(); i++) {
            String nextElement = nodeList.item(i).getTextContent();

            Assignment assignment = new Assignment(nextElement);
            assignments.add(assignment);
        }
        return assignments;
    }
}
