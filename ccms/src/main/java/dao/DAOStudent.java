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
    private Element elementLogin;
    private Element elementAcces;
    private Element elementName;
    private Element elementSurname;
    private Element elementPassword;
    private Element elementAssignmentID;
    private Element elementAssignmentURL;
    private Element elementAssignmentGrade;
    private Element elementAssignmentIsFinished;


    public DAOStudent() {
        this.connector = new Connector();
    }


    public Model get(String login) {
        element = connector.loadPerson(login);

        tempLogin = element.getAttribute("login");

        elementAcces = (Element) element.getElementsByTagName("accounttype").item(0);
        accessLevel = elementAcces.getTextContent();

        elementName = (Element) element.getElementsByTagName("name").item(0);
        tempName = elementName.getTextContent();

        elementSurname = (Element) element.getElementsByTagName("surname").item(0);
        tempSurname = elementSurname.getTextContent();

        elementPassword = (Element) element.getElementsByTagName("password").item(0);
        tempPassword = elementPassword.getTextContent();

        nodeList = element.getElementsByTagName("assignment");
        for(int i = 0; i < nodeList.getLength(); i++) {
            Element newElement = (Element) nodeList.item(i);

            elementAssignmentID = (Element) newElement.getElementsByTagName("name").item(0);
            tempAssignmentID = elementAssignmentID.getTextContent();

            elementAssignmentURL = (Element) newElement.getElementsByTagName("url").item(0);
            tempAssignmentURL = elementAssignmentURL.getTextContent();

            elementAssignmentGrade = (Element) newElement.getElementsByTagName("grade").item(0);
            tempAssignmentGrade = Integer.valueOf(elementAssignmentGrade.getTextContent());

            elementAssignmentIsFinished = (Element) newElement.getElementsByTagName("isFinished").item(0);
            tempAssignmentsIsFinished = Boolean.valueOf(elementAssignmentIsFinished.getTextContent());

            assignments.put(tempAssignmentID, new Assignment(tempAssignmentID, tempAssignmentURL, tempAssignmentGrade, tempAssignmentsIsFinished));
        }
        return new Model(tempName, tempSurname, accessLevel, tempPassword, tempLogin, assignments);
    }


    public void add(Model model) {
        connector.addPersonToXML(model);
    }


    public void delete(String login) {
        connector.deletePerson(login);
    }
}
