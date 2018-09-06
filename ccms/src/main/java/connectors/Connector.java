package connectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import java.io.File;
import java.lang.Exception;
<<<<<<< HEAD
=======

import containers.Assignment;
>>>>>>> d3859e8696b55a6210acf50153aa6e88a0f32959
import containers.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.HashMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.IOException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;

public class Connector {

    Model model = new Model();

    public void addAssignmentToXML(String assignmentName){

		Document doc;

        try {
            File xmlFile = new File("/java/XMLs/assignments.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
        } catch (IOException e) {
            System.out.println("Can't find the file");
        } catch (Exception e) {
			e.printStackTrace();
		}  

        Element root = doc.getDocumentElement(); // employees
        Element assignmentTag = (Element) root.getElementsByTagName("assignments").item(0); //employee
		Element newAssignment = doc.createElement("assignment");
        newAssignment.setAttribute("name", assignmentName);
        
        assignmentTag.appendChild(newAssignment);

        String outputURL = "/java/XMLs/assignments.xml";            
        DOMSource source = new DOMSource(doc);

	
		StreamResult result = new StreamResult(new FileOutputStream(outputURL));
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		
		transformer.transform(source, result);            
   
    }

    public void addPersonToXML(Model model){
        
        String accountType = model.get(accountType).toLowerCase();
        String name = model.get(name).toLowerCase();
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        String surname = model.get(surname).toLowerCase();
        String capitalizedSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        String login = model.get(login);
        String password = model.get(password);

        File xmlFile = new File("/java/XMLs/" + accountType +".xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        String tag = accountType.substring(0, accountType.length() - 1);

        Element root = doc.getDocumentElement(); // employees

        Element personTag = (Element) root.getElementsByTagName(accountType).item(0); //employee

		Element newPerson = doc.createElement(tag);
		newPerson.setAttribute("login", login);

        Element userName = doc.createElement("name");
        userName.setTextContent(capitalizedName);

        Element userSurname = doc.createElement("surname");
        userSurname.setTextContent(capitalizedSurname);

        Element userPassword = doc.createElement("password");
        userPassword.setTextContent(password);



        newPerson.appendChild(userName);
        newPerson.appendChild(userSurname);
        newPerson.appendChild(userPassword);
        
        if(accountType.equals("students")){
            HashMap<String, Assignment> assignments = model.get(assignments);
            Iterator<E> itr = assignments.keySet().iterator();
            while(itr.hasNext()){
                Element userAssignment = doc.createElement("assignment");
                Node assignmentName = doc.createElement("name");
                assignmentName.appendChild(doc.createTextNode(key));
                userAssignment.appendChild(assignmentName);
                Node grade = userAssignment.createElement("grade");
                grade.appendChild(doc.createTextNode(assignments.get(key)));
                userAssignment.appendChild(grade);
                newPerson.appendChild(userAssignment);
            }
            
        }

        personTag.appendChild(newPerson);
        
        try{
            String outputURL = "/java/XMLs/" + accountType +".xml";
            
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

        String[] filesSources = {"students.xml", "employees.xml", "mentors.xml"};
        String[] tags = {"student", "employee", "mentor"};

        String fileSource;
        String tag;
        Element person = null;

        for(int i=0; i<filesSources.length; i++){
            fileSource = filesSources[i];
            tag = tags[i];
            person = checkFileForUser(fileSource, tag, login);
        }
        return person;
    }

    public Element loadAssigments(){
        Document doc;
        try {
			File xmlFile = new File("/java/XMLs/assignments.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
			doc = dBuilder.parse(xmlFile);
        } catch(Exception e) {
            System.out.println(e);
        } 
			Element assignments = doc.getDocumentElement();
            return assignments;
    }

    private Element checkFileForPerson(String fileSource, String tag, String login){

		Document doc;

        try{
			File xmlFile = new File(fileSource);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
		} catch (Exception e){
			e.printStackTrace();
        }
        
        NodeList nList = doc.getElementsByTagName(tag);

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

    public Element loadListOfPersons(String accountType){
        Document doc;
        try {
			File xmlFile = new File("/java/XMLs/"+ accountType +".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
			doc = dBuilder.parse(xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        } 
            Element listOfUsers = doc.getDocumentElement();
            
            return listOfUsers;    
    }
    
    public void deletePerson(String login){

        String[] filesSources = {"students.xml", "employees.xml", "mentors.xml"};
        String[] tags = {"student", "employee", "mentor"};

        String fileSource;
        String tag;
        Element person = null;

        for(int i=0; i<filesSources.length; i++){
            fileSource = filesSources[i];
            tag = tags[i];
            person = checkFileForUser(fileSource, tag, login);
        }

        if(person != null) {
            Node parent = person.getParentNode();
            parent.removeChild(element);
            parent.normalize();
        }

        String outputURL = "/java/XMLs/"+ person.getLocalName() + "s" +".xml";            
        File xmlFile = new File(outputURL);
        Document doc = dBuilder.parse(xmlFile);

        DOMSource source = new DOMSource(doc);

	
		StreamResult result = new StreamResult(new FileOutputStream(outputURL));
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		
		transformer.transform(source, result);            
    }
}
