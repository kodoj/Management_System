package connectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class Connector {

    Model model;

    public void addAssignmentToXML(String fileName, String assignmentName){
        try{

            File xmlFile = new File("/java/XMLs/" fileName + ".xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            String tag = fileName.substring(0, str.length() - 1)            

            // Element root = doc.getDocumentElement(); // employees

            // Element TagName =  (Element) dataTag.getElementsByTagName(tag).item(0); // employee

            Element newPerson = doc.createElement(tag);

            Element firstName = doc.createElement("firstName");
            firstName.setTextContent("Tom");

            Element lastName = doc.createElement("lastName");
            lastName.setTextContent("Hanks");

            newPerson.appendChild(firstName);
            newPerson.appendChild(lastName);

            peopleTag.appendChild(newPerson);


        } catch (Exception e) {
            view.showMessage(e);
        }
    }

    public void saveXML(Model model) {

    }

    public void loadXML(String name, String surname, String password){

    }

    public void loadXML(String name, String surname){

    }
}
