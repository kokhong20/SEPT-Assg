package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;

public class SVGReader 
{
	private File fXmlFile;
	private Document doc;
	private DocumentBuilderFactory dbFactory;

	// Empty Constructor
	public SVGReader()
	{
		doc = null;
		fXmlFile = null;
		dbFactory = DocumentBuilderFactory.newInstance();
	}
	
	// set Document with dir from MenuAction.
	public void setDoc(String dir)
	{
		try
		{
			fXmlFile = new File(dir);
			dbFactory.setNamespaceAware(true);
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		}
		
		catch(Exception e)
		{
			
		}
		
		this.processFile();
	}
	
	public Document getDoc()
	{
		return doc;
	}
	
	// process whole .svg file and get tag name
	// to create instance object.
	public void processFile()
	{
		if(doc != null)
		{
			// Testing Line 
			/*NodeList lineList = this.getLines(doc);
			for(int i = 0; i < lineList.getLength(); i++)
			{
				Node lineNode = lineList.item(i);
				if(lineNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Lines newLine = new Lines(lineNode);
					//Testing
					System.out.println("x1 = " + newLine.getX1());
					System.out.println("x2 = " + newLine.getX2());
					System.out.println("y1 = " + newLine.getY1());
					System.out.println("y2 = " + newLine.getY2());
					//Testing
				}
			}*/
			
			// Testing purposes for circle 
			/*NodeList circleList = this.getCircles(doc);
			
			Circles circle;
			
			for(int i =0; i<circleList.getLength(); i++)
			{
				Node circleNode = circleList.item(i);
				System.out.println("\nCurrent Element :" + circleNode.getNodeName());
				
				circle = this.createCircle(circleNode);
				circle.readAttributes();
				System.out.println("CX is"+circle.getCX());
				System.out.println("gao tim");
			}*/
			
			// Testing Rectangle
			
			NodeList rectList = this.getRects(doc);
			for (int i=0; i<rectList.getLength(); i++)
			{
				this.createRect(rectList.item(i));
			}
		}
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
	
	// Called from main controller to create a new rect
	public Rectangles createRect(Node node)
	{
		Rectangles rect = new Rectangles(node);
		rect.readAttributes();
		return rect;
	}
	
	// Called from main controller to create a new circle
	public Circles createCircle(Node node)
	{
		Circles circle = new Circles(node);
		return circle;
	}
	
	
	// Called from main controller to create a new line
	public Lines createLine(Node node)
	{
		Lines line = new Lines(node);
		return line;
	}
	
}
