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
import java.io.IOException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;

public class Test {

    public void addAssignmentToXML(String assignmentName){
		
		Document doc;

		try {
            File xmlFile = new File("assignments.xml");
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

		String outputURL = "assignments.xml";            
		DOMSource source = new DOMSource(doc);

	
		StreamResult result = new StreamResult(new FileOutputStream(outputURL));
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		
		transformer.transform(source, result);
   
    }

    public void addPersonToXML(){
        
        String accountType = "students";
        String name = "Karol";
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        String surname = "Trzaska";
        String capitalizedSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        String login = "karoltrzaskawtibie";
        String password = "admin123";

        if(accountType.equals("students")){
            HashMap<String, Assignment> assignments = model.get(assignments);
        }


        File xmlFile = new File("" + accountType +".xml");
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
        
        personTag.appendChild(newPerson);
        
        try{
            String outputURL = "" + accountType +".xml";
            
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
			Element person;

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
			File xmlFile = new File("assignments.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
			doc = dBuilder.parse(xmlFile);
        } catch(Exception e) {
            System.out.println(e);
        } 
			Element assignments = doc.getDocumentElement();
            return assignments;
    }

    public Element checkFileForUser(String fileSource, String tag, String login){

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
}

