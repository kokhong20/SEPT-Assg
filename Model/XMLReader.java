package Model;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLReader {
	
	private File openedFile;
	
	public XMLReader(File openedFile) {
		
		this.openedFile = openedFile;
		
		try {
			
			DocumentBuilderFactory dbFactory  = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(openedFile);
			doc.getDocumentElement().normalize();
			System.out.println(doc.getDocumentElement().getNodeName());
		}
		
		catch (Exception e){
			
		}
		
	}
}
