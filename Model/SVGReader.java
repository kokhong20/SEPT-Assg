package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.File;
import java.io.FileNotFoundException;

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
		long startTime = System.nanoTime();
		try
		{
			fXmlFile = new File(dir);

			dbFactory.setNamespaceAware(true);		
			
			dbFactory.setFeature("http://xml.org/sax/features/namespaces", false);
			dbFactory.setFeature("http://xml.org/sax/features/validation", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
			dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			long endTime = System.nanoTime();
			System.out.println("Time: " + (endTime - startTime)/1000000);
		}
		
		catch(FileNotFoundException e)
		{
			//JOptionPane.showInternalMessageDialog(desktopPane, "File Not Found", "File Not Found", JOptionPane.ERROR_MESSAGE);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
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
			/*// Testing Line 
			NodeList lineList = this.getLines(doc);
			for(int i = 0; i < lineList.getLength(); i++)
			{
				Node lineNode = lineList.item(i);
				if(lineNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Lines newLine = new Lines(lineNode);
					//Testing
					System.out.println("stroke = " + newLine.getStrokeColor());
					System.out.println("stroke-width = " + newLine.getStrokeWidth());
					System.out.println("x1 = " + newLine.getX1());
					System.out.println("x2 = " + newLine.getX2());
					System.out.println("y1 = " + newLine.getY1());
					System.out.println("y2 = " + newLine.getY2());
					//Testing
				}
			}
			
			// Testing purposes for circle 
			NodeList circleList = this.getCircles(doc);			
			for(int i =0; i<circleList.getLength(); i++)
			{
				Node circleNode = circleList.item(i);
				if(circleNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Circles newCircle = new Circles(circleNode);
					//Testing
					System.out.println("stroke = " + newCircle.getStrokeColor());
					System.out.println("stroke-width = " + newCircle.getStrokeWidth());
					System.out.println("fill = " + newCircle.getFill());
					System.out.println("cx = " + newCircle.getCX());
					System.out.println("cy = " + newCircle.getCY());
					System.out.println("r = " + newCircle.getR());
					//Testing
				}
			}
			
			// Testing Rectangle
			
			NodeList rectList = this.getRects(doc);
			for (int i=0; i<rectList.getLength(); i++)
			{
				Node rectNode = rectList.item(i);
				if(rectNode.getNodeType() == Node.ELEMENT_NODE)
				{
					Rectangles newRect = new Rectangles(rectNode);
					//Testing
					System.out.println("stroke = " + newRect.getStrokeColor());
					System.out.println("stroke-width = " + newRect.getStrokeWidth());
					System.out.println("fill = " + newRect.getFill());
					System.out.println("x = " + newRect.getX());
					System.out.println("y = " + newRect.getY());
					System.out.println("width = " + newRect.getWidth());
					System.out.println("height = " + newRect.getHeight());
					//Testing
				}
			}*/
			
			NodeList nList = doc.getChildNodes();
			NodeList sList = nList.item(0).getChildNodes();
			for (int i=0; i<sList.getLength(); i++)
			{
				//System.out.println(sList.item(i).getNodeName());
				if (sList.item(i).getNodeName().equals("rect"))
				{
					Rectangles newRect = new Rectangles(sList.item(i));
					//Testing
					System.out.println("stroke = " + newRect.getStrokeColor());
					System.out.println("stroke-width = " + newRect.getStrokeWidth());
					System.out.println("fill = " + newRect.getFill());
					System.out.println("x = " + newRect.getX());
					System.out.println("y = " + newRect.getY());
					System.out.println("width = " + newRect.getWidth());
					System.out.println("height = " + newRect.getHeight());
					System.out.println("\n");
					//Testing
				}
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
	/*public Rectangles createRect(Node node)
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
	}*/
	
}
