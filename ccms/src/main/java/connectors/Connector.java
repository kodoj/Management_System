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


    public void addAssignmentToXML(String assignmentName) {

        Document doc = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("src/main/java/XMLs/assignments.xml");
            doc.getDocumentElement().normalize();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file");
        } catch (Exception e) {
            System.out.println("There was a trouble with loading ur file");
        }

        Element root = doc.getDocumentElement();
        Element newAssignment = doc.createElement("assignment");
        newAssignment.setTextContent(assignmentName);

        root.appendChild(newAssignment);

        String outputURL = "src/main/java/XMLs/assignments.xml";
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
        System.out.println(accountType);
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
            doc = dBuilder.parse("src/main/java/XMLs/" + accountType + "s.xml");
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Missing file");
            return;
        }

        Element personTag = (Element) doc.getElementsByTagName(accountType + "s").item(0); //employee

        Element newPerson = doc.createElement(accountType);
        newPerson.setAttribute("login", login);

        Element userName = doc.createElement("name");
        userName.setTextContent(capitalizedName);

        Element userSurname = doc.createElement("surname");
        userSurname.setTextContent(capitalizedSurname);

        Element userPassword = doc.createElement("password");
        userPassword.setTextContent(password);

        Element userTypeElement = doc.createElement("accounttype");
        userTypeElement.setTextContent(accountType);

        newPerson.appendChild(userName);
        newPerson.appendChild(userSurname);
        newPerson.appendChild(userPassword);
        newPerson.appendChild(userTypeElement);

        if (accountType.equals("student")) {
            Map<String, Assignment> assignments = model.getAssignments();
            Iterator<String> itr = assignments.keySet().iterator();
            Element userAssignments = doc.createElement("assignments");
            while (itr.hasNext()) {
                String key = (String) itr.next();
                Element userAssignment = doc.createElement("assignment");
                Node assignmentName = doc.createElement("name");
                Node grade = doc.createElement("grade");
                Node url = doc.createElement("url");
                Node isFinished = doc.createElement("isFinished");
                grade.appendChild(doc.createTextNode(Integer.toString(assignments.get(key).getGrade())));
                assignmentName.appendChild(doc.createTextNode(key));
                url.appendChild(doc.createTextNode(assignments.get(key).getUrl()));
                isFinished.appendChild(doc.createTextNode(Boolean.toString(assignments.get(key).getIsFinished())));
                userAssignment.appendChild(assignmentName);
                userAssignment.appendChild(grade);
                userAssignment.appendChild(url);
                userAssignment.appendChild(isFinished);

                userAssignments.appendChild(userAssignment);
            }
            newPerson.appendChild(userAssignments);

        }

        personTag.appendChild(newPerson);

        try {
            String outputURL = "src/main/java/XMLs/" + accountType + "s.xml";

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

        String[] filesSources = {"students.xml", "employees.xml", "mentors.xml", "administrators.xml"};
        String[] tags = {"student", "employee", "mentor", "administrator"};

        String fileSource;
        String tag;
        Element person = null;

        for (int i = 0; i < filesSources.length; i++) {
            fileSource = filesSources[i];
            tag = tags[i];
            person = checkFileForPerson(fileSource, tag, login);
            if (person == null) {
                continue;
            } else {
                return person;
            }
        }
        return person;
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


    public NodeList loadAssigments() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("src/main/java/XMLs/assignments.xml");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
        NodeList listOfAssignments = doc.getElementsByTagName("assignment");
        return listOfAssignments;
    }


    public NodeList loadListOfPersons(String accountType) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("src/main/java/XMLs/" + accountType + "s.xml");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
        NodeList listOfUsers = doc.getElementsByTagName(accountType);

        return listOfUsers;
    }

    public void deletePerson(String login) {
        Element element = loadPerson(login);

        Element elementAcces = (Element) element.getElementsByTagName("accounttype").item(0);
        String accountType = elementAcces.getTextContent();

        Document doc = null;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse("src/main/java/XMLs/"+ accountType + "s.xml");
            doc.getDocumentElement().normalize();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find the file");
        } catch (Exception e) {
            System.out.println("There was a trouble with loading ur file");
        }

        NodeList nodes = doc.getElementsByTagName(accountType);

        for (int i = 0; i < nodes.getLength(); i++) {
            Element newElement = (Element) nodes.item(i);
            if (newElement.getAttribute("login").equals(login)) {
                while (nodes.item(i).hasChildNodes()) {
                    nodes.item(i).removeChild(nodes.item(i).getFirstChild());
                }
                nodes.item(i).getParentNode().removeChild(nodes.item(i));
            }
        }

        try {
            String saveURL = "src/main/java/XMLs/" + accountType + "s.xml";

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(saveURL));

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();

            transformer.transform(source, result);

        } catch (FileNotFoundException e) {
            System.out.println("File was not found!");
        } catch (Exception e) {
            System.out.println("Can't find the file");
        }
    }
}
