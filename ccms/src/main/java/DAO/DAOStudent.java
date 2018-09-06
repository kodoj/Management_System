package DAO;

import connectors.Connector;
import containers.Assignment;
import containers.Model;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

public class DAOStudent implements DAOSingleObject {

    private Element element;
    private Connector connector;
    private String tempName;
    private String tempSurname;
    private String accessLevel;
    private String tempPassword;
    private String tempLogin;
    private NodeList nodeList;
    private Map<String, Assignment> assignments = new HashMap<String, Assignment>();

    private int assignmentIDIndex = 0;
    private int assignmentURLIndex = 1;
    private int assignmentGradeIndex = 2;
    private int assignmentIsFinished = 3;

    public DAOStudent() {
        this.connector = new Connector();
    }


    public Model get(String login) {
        element = connector.loadPerson(login);
        tempLogin = element.getAttribute("login");
        accessLevel = element.
        tempName = element.getElementsByTagName("name").item(0).getTextContent();
        tempSurname = element.getElementsByTagName("surname").item(0).getTextContent();
        tempPassword = element.getElementsByTagName("password").item(0).getTextContent();
        for(int i = 0; i < element.getElementsByTagName("assignments").getLength(); i++) {
            nodeList = element.getElementsByTagName("assignments").item(i).getChildNodes();
            assignments.put(nodeList.item(assignmentIDIndex).getTextContent(), new Assignment(nodeList.item(assignmentIDIndex).getTextContent(),
                    nodeList.item(assignmentURLIndex).getTextContent(), Integer.valueOf(nodeList.item(assignmentGradeIndex).getTextContent()),
                    Boolean.parseBoolean(nodeList.item(assignmentIsFinished).getTextContent()));
        }
        return new Model(tempName, tempSurname, accessLevel, tempPassword, tempLogin, assignments);
    }


    public void add(Model model) {
        connector.addPersonToXML(model);
    }


    public void delete(String login) {

    }
}
