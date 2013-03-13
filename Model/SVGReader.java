package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class SVGReader 
{

	// Empty Constructor
	public SVGReader()
	{
	}

	// Called from main controller to determine
	// whether file is valid or not, return null if not valid
	// else, return the whole doc
	public Document isSVG(String dir)
	{
		Document doc = null;
		try
		{
			File fXmlFile = new File(dir);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			dbFactory.setNamespaceAware(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		}
		catch(Exception e)
		{
			
		}
		
		return doc;
	}

	// If no line, return NodeList with 0 length, 
	// else return NodeList containing all rect tags 
	public NodeList getRects(Document doc)
	{
		return doc.getElementsByTagName("rect");
	}

	// If no line, return NodeList with 0 length, 
	// else return NodeList containing all circle tags 
	public NodeList getCircles(Document doc)
	{
		return doc.getElementsByTagName("circle");
	}

	// If no line, return NodeList with 0 length, 
	// else return NodeList containing all line tags 
	public NodeList getLines(Document doc)
	{
		return doc.getElementsByTagName("line");
	}
	/*
	// Called from main controller to create a new rect
	public Rectangles createRect(Node node)
	{
		Rectangles rect = new Rectangles(null, 0, 0, 0, 0, 0);
		return rect;
	}
	
	// Called from main controller to create a new circle
	public Circles createCircle(Node node)
	{
		Circles circle = new Circles(null, 0, 0, 0, 0, 0);
		return circle;
	}
	*/
	
	// Called from main controller to create a new line
	public Lines createLine(Node node)
	{
		Lines line = new Lines(null, 0, 0, 0, 0, 0);
		return line;
	}
	
}
