package dao;

import connectors.Connector;
import containers.Model;
import org.w3c.dom.Element;

public class DAOEmployer implements DAOSingleObject {

    private Element element;
    private Connector connector;
    private String tempName;
    private String tempSurname;
    private String accessLevel;
    private String tempPassword;
    private String tempLogin;
    private Element elementLogin;
    private Element elementAcces;
    private Element elementName;
    private Element elementSurname;
    private Element elementPassword;

    public DAOEmployer() {
        this.connector = new Connector();
    }

    public Model get(String login) {
        element = connector.loadPerson(login);

        elementLogin = (Element) element.getElementsByTagName("login").item(0);
        tempLogin = elementLogin.getTextContent();

        elementAcces = (Element) element.getElementsByTagName("accounttype").item(0);
        accessLevel = elementAcces.getTextContent();

        elementName = (Element) element.getElementsByTagName("name").item(0);
        tempName = elementName.getTextContent();

        elementSurname = (Element) element.getElementsByTagName("surname").item(0);
        tempSurname = elementSurname.getTextContent();

        elementPassword = (Element) element.getElementsByTagName("password").item(0);
        tempPassword = elementPassword.getTextContent();

        return new Model(tempName, tempSurname, accessLevel, tempPassword, tempLogin);
    }

    public void add(Model model) {
        connector.addPersonToXML(model);
    }

    public void delete(String login) {

    }
}
