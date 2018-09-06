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

    private String tempLogin;
    private String tempName;
    private String tempSurname;

    public DAOLists() {
        this.connector = new Connector();
        this.models =  new ArrayList<Model>();
        this.assignments = new ArrayList<Assignment>();
    }

    public List<Model> getAllStudents() {
        element = connector.loadListOfUsers("students");
        nodeList = element.getElementsByTagName("student");

        for(int i = 0; i < nodeList.getLength(); i++) {
            tempLogin = element.g
            tempName = element.getElementsByTagName("name").item(i).getTextContent();
            tempSurname = element.getElementsByTagName("surname").item(i).getTextContent();
        }
    }

    public List<Model> getAllEmployers() {
        return null;
    }

    public List<Assignment> getAllAssignments() {
        element = connector.loadAssigments();
        nodeList = element.getElementsByTagName("assignment");
        for(int i = 0; i < nodeList.getLength(); i++) {
            assignment = new Assignment(nodeList.item(i).getAttributes().getNamedItem("assignment").getNodeValue());
            assignments.add(assignment);
        }

    }
}
