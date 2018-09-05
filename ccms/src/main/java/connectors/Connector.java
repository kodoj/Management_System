package connectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.Exception;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class Connector {

    Model model;

    public void addAssignmentToXML(String assignmentName){

        try {

            File xmlFile = new File("/java/XMLs/assignments.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();      

        Element root = doc.getDocumentElement(); // employees
        Element assignmentTag = (Element) root.getElementsByTagName("assignments").item(0); //employee
        Element newAssignment = doc.createElement("assignment").setAttribute("name", assignmentName);

        assignmentTag.appendChild(newAssignment);

        try {
            String outputURL = "/java/XMLs/assignments.xml";
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(outputURL));
            
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            
            transformer.transform(source, result);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void addPersonToXML(Model model){
        
        String accountType = model.get(accountType).toLowerCase();
        String name = model.get(name).toLowerCase();
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        String surname = model.get(surname).toLowerCase();
        String capitalizedSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        String login = model.get(login);
        String password = model.get(password);

        if(accountType.equals("students")){
            HashMap<String, Assignment> assignments = model.get(assignments);
        }


        File xmlFile = new File("/java/XMLs/" + personType +".xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();

        String tag = personType.substring(0, str.length() - 1);

        Element root = doc.getDocumentElement(); // employees

        Element personTag = (Element) root.getElementsByTagName(accountType).item(0); //employee

        Element newPerson = doc.createElement(tag).setAttribute("login", login);

        Element userName = doc.createElement("name");
        userName.setTextContent(capitalizedName);

        Element userSurname = doc.createElement("surname");
        userSurname.setTextContent(capitalizedSurname);

        Element userPassword = doc.createElement("password");
        userPassword.setTextContent(password);

        newPerson.appendChild(userName);
        newPerson.appendChild(userSurname);
        newPerson.appendChild(userPassword);
        
        personTag.appendChild(newPerson);
        
        try{
            String outputURL = "/java/XMLs/" + personType +".xml";
            
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(outputURL));
            
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Element loadPerson(String login){

			File xmlFile = new File("/java/XMLs/employees.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            doc.getDocumentElement().normalize();

            String[] filesSources = {"/java/XMLs/students.xml", "/java/XMLs/employees.xml", "/java/XMLs/mentors.xml"};
            String[] tags = {"student", "employee", "mentor"};

            Strig fileSource;
            String tag;

            for(int i=0; i<filesSources.length; i++){
                fileSource = filesSources[i];
                tag = tags[i];
                Element person = checkFileForUser(fileSource, tag);
            }


            checkFileForUser(fileSource, tag);    

            return person;

    }

    public Element loadAssigments(){
        try {
			File xmlFile = new File("/java/XMLs/assignments.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
        } catch(Exception e) {
            System.out.println(e);
        } 
			Document doc = dBuilder.parse(xmlFile);
			Element assignments = doc.getDocumentElement().normalize();
            return assisgnments;
    }

    public Element checkFileForUser(String fileSource, String tag){

        File xmlFile = new File(fileSource);
        Document doc = dBuilder.parse(xmlFile);
        Node nList = doc.getElementsByTagName(tag);

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
                                            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                if(eElement.getAttribute("login").equals(login)){
                    return eElement;
                }
            }
        }

    }
}
