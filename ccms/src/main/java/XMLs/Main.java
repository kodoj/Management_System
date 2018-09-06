import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
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
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;

class Main {
    public static void main(String[] args) {
        System.out.println("Dziala");
        
        deleteUser("xxxpenetratorxxx");    
        System.out.println("Ididit");
    }

    public static void deleteUser(String login){

        Element person = loadPerson(login);
        Element personType = (Element) person.getElementsByTagName("accounttype").item(0);
        String accounttype = personType.getTextContent();

        if(person != null) {
            Node parent = person.getParentType();
            parent.removeChild(person);
            parent.normalize();
        }

        String outputURL = accounttype + "s" +".xml";
        Document doc = null;
        try {            
            File xmlFile = new File(outputURL);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new FileOutputStream(outputURL));
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            
            transformer.transform(source, result);            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static Element loadListOfUsers(String accountType){
        Document doc = null;
        try {
			File xmlFile = new File(""+ accountType +".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();  
			doc = dBuilder.parse(xmlFile);
        } catch(Exception e) {
            e.printStackTrace();
        } 
            Element listOfUsers = doc.getDocumentElement();
            
            return listOfUsers;    
    }

    public static Element loadAssigments(){
        Document doc = null;
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

    private static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
          Transformer t = TransformerFactory.newInstance().newTransformer();
          t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
          t.setOutputProperty(OutputKeys.INDENT, "yes");
          t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (Exception te) {
          System.out.println("nodeToString Transformer Exception");
        }
        return sw.toString();
      }
    

    public static Element loadPerson(String login){

        String[] filesSources = {"students.xml", "employees.xml", "mentors.xml"};
        String[] tags = {"student", "employee", "mentor"};

        String fileSource;
        String tag;
        Element person = null;

        for(int i=0; i<filesSources.length; i++){
            fileSource = filesSources[i];
            tag = tags[i];
            person = checkFileForUser(fileSource, tag, login);            
            if(person == null){
                System.out.println("person loaded with null");
            } else {
                System.out.println("person loaded good!");
                return person;
            }
        }
        
        return person;
    }

    
    private static Element checkFileForUser(String fileSource, String tag, String login){

		Document doc = null;

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

        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);
                                            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                if(eElement.getAttribute("login").equals(login)){
                    return eElement;
                }
            }
        }
        return null;
    }
}