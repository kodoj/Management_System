package dao;

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
    private String tempAssignmentID;
    private String tempAssignmentURL;
    private int tempAssignmentGrade;
    private boolean tempAssignmentsIsFinished;


    public DAOStudent() {
        this.connector = new Connector();
    }


    public Model get(String login) {
        element = connector.loadPerson(login);
        tempLogin = element.getAttribute("login");
        accessLevel = element.getElementsByTagName("accounttype").item(0).getTextContent();
        tempName = element.getElementsByTagName("name").item(0).getTextContent();
        tempSurname = element.getElementsByTagName("surname").item(0).getTextContent();
        tempPassword = element.getElementsByTagName("password").item(0).getTextContent();

        nodeList = element.getElementsByTagName("assignment");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element newElement = (Element) nodeList.item(i);
            tempAssignmentID = newElement.getElementsByTagName("name").item(0).getTextContent();
            tempAssignmentURL = newElement.getElementsByTagName("url").item(0).getTextContent();
            tempAssignmentGrade = Integer.valueOf(newElement.getElementsByTagName("grade").item(0).getTextContent());
            tempAssignmentsIsFinished = Boolean.valueOf(newElement.getElementsByTagName("isFinished").item(0).getTextContent());
            assignments.put(tempAssignmentID, new Assignment(tempAssignmentID, tempAssignmentURL, tempAssignmentGrade, tempAssignmentsIsFinished));
        }
        return new Model(tempName, tempSurname, accessLevel, tempPassword, tempLogin, assignments);
    }


    public void add(Model model) {
        connector.addPersonToXML(model);
    }


    public void delete(String login) {

    }
}
