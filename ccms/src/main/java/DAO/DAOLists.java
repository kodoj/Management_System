package DAO;

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

    public DAOLists() {
        this.connector = new Connector();
        this.models =  new ArrayList<Model>;
        this.assignments = new ArrayList<Assignment>;
    }

    public List<Model> getAllStudents() {

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
