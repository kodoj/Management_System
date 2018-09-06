package connectors;

import containers.Assignment;
import containers.Model;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Map;


public class Connector {

    Model model;

    public void addAssignmentToXML(String assignmentName) {

        Document doc = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("/java/XMLs/assignments.xml");
            doc.getDocumentElement().normalize();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file");
        } catch (Exception e) {
            System.out.println("There was a trouble with loading ur file");
        }

        Element root = doc.getDocumentElement(); // employees
        Element assignmentTag = (Element) root.getElementsByTagName("assignments").item(0); //employee
        Element newAssignment = doc.createElement("assignment");
        newAssignment.setAttribute("name", assignmentName);

        root.appendChild(newAssignment);

        String outputURL = "/java/XMLs/assignments.xml";
        DOMSource source = new DOMSource(doc);

        try {
            StreamResult result = new StreamResult(new FileOutputStream(outputURL));
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            transformer.transform(source, result);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (Exception e) {
            System.out.println("problems with loading file");
        }

    }

    public void addPersonToXML(Model model) {

        String accountType = model.getAccountType().toLowerCase();
        String name = model.getName().toLowerCase();
        String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        String surname = model.getSurname().toLowerCase();
        String capitalizedSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1);
        String login = model.getLogin();
        String password = model.getPassword();
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("/java/XMLs/" + accountType + "s.xml");
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Missing file");
        }
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

        if (accountType.equals("students")) {
            Map<String, Assignment> assignments = model.getAssignments();
            Iterator<String> itr = assignments.keySet().iterator();
            while (itr.hasNext()) {
                String key = (String) itr.next();
                Element userAssignment = doc.createElement("assignment");
                Node assignmentName = doc.createElement("name");
                assignmentName.appendChild(doc.createTextNode(key));
                userAssignment.appendChild(assignmentName);
                Node grade = doc.createElement("grade");
                grade.appendChild(doc.createTextNode(Integer.toString(assignments.get(key).getGrade())));
                userAssignment.appendChild(grade);
                newPerson.appendChild(userAssignment);
            }

        }

        personTag.appendChild(newPerson);

        try {
            String outputURL = "/java/XMLs/" + accountType + ".xml";

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(outputURL));

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();

            transformer.transform(source, result);

        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
    }

    public Element loadPerson(String login) {

        String[] filesSources = {"students.xml", "employees.xml", "mentors.xml"};
        String[] tags = {"student", "employee", "mentor"};

        String fileSource;
        String tag;
        Element person = null;

        for (int i = 0; i < filesSources.length; i++) {
            fileSource = filesSources[i];
            tag = tags[i];
            person = checkFileForPerson(fileSource, tag, login);
            if (person == null) {
                System.out.println("person loaded with null");
            } else {
                System.out.println("person loaded good!");
                return person;
            }
        }

        return person;
    }

    public Element loadAssigments() {
        Document doc = null;
        try {
            File xmlFile = new File("/java/XMLs/assignments.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("/java/XMLs/assignments.xml");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
        Element assignments = doc.getDocumentElement();
        return assignments;
    }

    private Element checkFileForPerson(String fileSource, String tag, String login) {

        Document doc = null;
        fileSource = "src/main/java/XMLs/" + fileSource;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fileSource);
            doc.getDocumentElement().normalize();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
            e.printStackTrace();
            System.out.println(fileSource);
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }

        NodeList nList = doc.getElementsByTagName(tag);

        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                if (eElement.getAttribute("login").equals(login)) {
                    return eElement;
                }
            }
        }
        return null;
    }

    public Element loadListOfPersons(String accountType) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("/java/XMLs/" + accountType + ".xml");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
        Element listOfUsers = doc.getDocumentElement();

        return listOfUsers;
    }

    public void deletePerson(String login) {
        Element element = loadPerson(login);

        Element elementAcces = (Element) element.getElementsByTagName("accounttype").item(0);
        String accessLevel = elementAcces.getTextContent();

        String outputURL = "/java/XMLs/"+ accessLevel + "s.xml";

        Document doc = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(outputURL);
            doc.getDocumentElement().normalize();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file");
        } catch (Exception e) {
            System.out.println("There was a trouble with loading ur file");
        }


        NamedNodeMap nodes = doc.getAttributes();

        for (int i = 0; i < nodes.getLength(); i++) {

            if (nodes.item(i).getNodeValue().equals(login)) {
                while (nodes.item(i).hasChildNodes()) {
                    nodes.item(i).removeChild(nodes.item(i).getFirstChild());
                }
            }
        }
    }
}
