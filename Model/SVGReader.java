package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class SVGReader 
{
	private File fXmlFile;
	private Document doc;
	private DocumentBuilderFactory dbFactory;
	public static LinkedList <Rectangle2D.Double> rectShapeList = new LinkedList<Rectangle2D.Double>();
	public static LinkedList <Ellipse2D.Double> circleShapeList = new LinkedList<Ellipse2D.Double>();


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
			Node svg = doc.getElementsByTagName("svg").item(0);
			NodeList drawList = svg.getChildNodes();
			System.out.println(drawList.getLength());
			for (int index = 0; index < drawList.getLength(); index++)
			{
				switch(drawList.item(index).getNodeName())
				{
					case "rect":
						Rectangles newRect = new Rectangles(drawList.item(index));
						//Add new rectangle object to Shapes and put into linked list
						Rectangle2D.Double rectShape = new Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
						rectShapeList.add(rectShape);
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
						break;
					case "circle":
						Circles newCircle = new Circles(drawList.item(index));
						//Add new circle object to Shapes and put into linked List
						Ellipse2D.Double circleShape = new Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);
						System.out.println("x is"+newCircle.getEllipse2DX());
						circleShapeList.add(circleShape);
						//Testing
						System.out.println("stroke = " + newCircle.getStrokeColor());
						System.out.println("stroke-width = " + newCircle.getStrokeWidth());
						System.out.println("fill = " + newCircle.getFill());
						System.out.println("cx = " + newCircle.getCX());
						System.out.println("cy = " + newCircle.getCY());
						System.out.println("r = " + newCircle.getR());
						System.out.println("\n");
						//Testing
						break;
					case "line":
						Lines newLine = new Lines(drawList.item(index));
						//Testing
						System.out.println("stroke = " + newLine.getStrokeColor());
						System.out.println("stroke-width = " + newLine.getStrokeWidth());
						System.out.println("x1 = " + newLine.getX1());
						System.out.println("x2 = " + newLine.getX2());
						System.out.println("y1 = " + newLine.getY1());
						System.out.println("y2 = " + newLine.getY2());
						System.out.println("\n");
						//Testing
						break;
					case "g":
						NodeList gList = drawList.item(index).getChildNodes();
						System.out.println("<g>");
						for(int gIndex = 0; gIndex < gList.getLength(); gIndex++)
						{
							switch(gList.item(gIndex).getNodeName())
							{
								case "rect":
									Rectangles gRect = new Rectangles(gList.item(gIndex));
									//Add new rectangle object to Shapes and put into linked list
									Rectangle2D.Double rectGShape = new Rectangle2D.Double(gRect.getX(),gRect.getY(),gRect.getWidth(),gRect.getHeight());
									rectShapeList.add(rectGShape);

									//Testing
									System.out.println("stroke = " + gRect.getStrokeColor());
									System.out.println("stroke-width = " + gRect.getStrokeWidth());
									System.out.println("fill = " + gRect.getFill());
									System.out.println("x = " + gRect.getX());
									System.out.println("y = " + gRect.getY());
									System.out.println("width = " + gRect.getWidth());
									System.out.println("height = " + gRect.getHeight());
									System.out.println("\n");
									//Testing
									break;
								case "circle":
									Circles gCircle = new Circles(gList.item(gIndex));
									//Add new circle object to Shapes and put into linked List
									Ellipse2D.Double circleGShape = new Ellipse2D.Double(gCircle.getEllipse2DX(),gCircle.getEllipse2DY(),gCircle.getR()*2,gCircle.getR()*2);
									circleShapeList.add(circleGShape);
									//Testing
									System.out.println("stroke = " + gCircle.getStrokeColor());
									System.out.println("stroke-width = " + gCircle.getStrokeWidth());
									System.out.println("fill = " + gCircle.getFill());
									System.out.println("cx = " + gCircle.getCX());
									System.out.println("cy = " + gCircle.getCY());
									System.out.println("r = " + gCircle.getR());
									System.out.println("\n");
									//Testing
									break;
								case "line":
									Lines gLine = new Lines(gList.item(gIndex));
									//Testing
									System.out.println("stroke = " + gLine.getStrokeColor());
									System.out.println("stroke-width = " + gLine.getStrokeWidth());
									System.out.println("x1 = " + gLine.getX1());
									System.out.println("x2 = " + gLine.getX2());
									System.out.println("y1 = " + gLine.getY1());
									System.out.println("y2 = " + gLine.getY2());
									System.out.println("\n");
									//Testing
									break;
							}
						}
						System.out.println("</g>");
						break;
				}
				//System.out.println(sList.item(i).getNodeName());
				/*
				if (drawList.item(index).getNodeName().equals("rect"))
				{
					Rectangles newRect = new Rectangles(drawList.item(index));
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
				}*/
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
