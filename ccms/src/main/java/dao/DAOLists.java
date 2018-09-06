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
        element = connector.loadListOfUsers(userType + "s");
        nodeList = element.getElementsByTagName(userType);

        for(int i = 0; i < nodeList.getLength(); i++) {
            tempLogin = element.getElementsByTagName("accounttype").item(i).getTextContent();
            tempName = element.getElementsByTagName("name").item(i).getTextContent();
            tempSurname = element.getElementsByTagName("surname").item(i).getTextContent();
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
