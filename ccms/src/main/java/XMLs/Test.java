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

class Test {
	public static void main(String[] args){
		try {
			File xmlFile = new File("assignments.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			Element assignments = doc.getDocumentElement();
            System.out.println(assignments);
        } catch(Exception e) {
            System.out.println(e);
        } 

	}
}
