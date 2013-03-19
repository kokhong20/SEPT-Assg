package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.LinkedList;
import java.util.LinkedHashSet;

public class SVGReader 
{
	private File fXmlFile;
	private Document doc;
	private DocumentBuilderFactory dbFactory;
	//public static LinkedList <Drawings> drawingCollection = new LinkedList<Drawings>();
	private LinkedHashSet <Drawings> drawingCollection;
	private double svgWidth;
	private double svgHeight;

	public SVGReader()
	{
		doc = null;
		fXmlFile = null;
		dbFactory = DocumentBuilderFactory.newInstance();
		this.drawingCollection = new LinkedHashSet<Drawings>();
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
			this.setSize(((Element) svg).getAttribute("width"), ((Element) svg).getAttribute("height"));
			NodeList drawList = svg.getChildNodes();
			System.out.println("Length : " + drawList.getLength());
			for (int index = 0; index < drawList.getLength(); index++)
			{
				System.out.println("Node Name : " + drawList.item(index).getNodeName());
				
				
				
				switch(drawList.item(index).getNodeName())
				{
					case "rect":
						Rectangles newRect = new Rectangles(drawList.item(index));
						//Add new rectangle object to Shapes and put into linked list
						//Rectangle2D.Double rectShape = new Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
						this.drawingCollection.add(newRect);
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
						//Ellipse2D.Double circleShape = new Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);
						System.out.println("x is"+newCircle.getEllipse2DX());
						this.drawingCollection.add(newCircle);
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
						this.drawingCollection.add(newLine);
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
						createGroups(this.drawingCollection, gList, drawList.item(index), null, null);
						
						/*NodeList gList = drawList.item(index).getChildNodes();
						System.out.println("<g>");
						for(int gIndex = 0; gIndex < gList.getLength(); gIndex++)
						{
							switch(gList.item(gIndex).getNodeName())
							{
								case "rect":
									Rectangles gRect = new Rectangles(gList.item(gIndex));
									//Add new rectangle object to Shapes and put into linked list
									//Rectangle2D.Double rectGShape = new Rectangle2D.Double(gRect.getX(),gRect.getY(),gRect.getWidth(),gRect.getHeight());
									this.drawingCollection.add(gRect);

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
									//Ellipse2D.Double circleGShape = new Ellipse2D.Double(gCircle.getEllipse2DX(),gCircle.getEllipse2DY(),gCircle.getR()*2,gCircle.getR()*2);
									this.drawingCollection.add(gCircle);
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
									this.drawingCollection.add(gLine);
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
						}*/
						System.out.println("</g>");
						break;
				}
			}
		}
	}

	private void createGroups(LinkedHashSet<Drawings> collection,
			NodeList gList, Node gNode, Color stroke, Color fill) {
		// TODO Auto-generated method stub
		//Color stroke = null;
		//Color fill = null;
		if(((Element) gNode).hasAttribute("stroke"))
			stroke = Coloring.setColor(((Element) gNode).getAttribute("stroke"));
		if(((Element) gNode).hasAttribute("fill"))
			fill = Coloring.setColor(((Element) gNode).getAttribute("fill"));
		
		for(int i = 0; i < gList.getLength(); i++)
		{
			if(gList.item(i).getNodeName().equals("g"))
				createGroups(collection, gList.item(i).getChildNodes(), gList.item(i), stroke, fill);
			else
			{
				switch(gList.item(i).getNodeName())
				{
					case "rect":
						Rectangles newRect = new Rectangles(gList.item(i));
						//Add new rectangle object to Shapes and put into linked list
						//Rectangle2D.Double rectShape = new Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
						if(newRect.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newRect.setStrokeColor(stroke);

						if(newRect.getFill().equals(Color.BLACK) && fill != null)
							newRect.setFill(fill);
						
						collection.add(newRect);
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
						Circles newCircle = new Circles(gList.item(i));
						//Add new circle object to Shapes and put into linked List
						//Ellipse2D.Double circleShape = new Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);
						if(newCircle.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newCircle.setStrokeColor(stroke);

						if(newCircle.getFill().equals(Color.BLACK) && fill != null)
							newCircle.setFill(fill);
						
						System.out.println("x is"+newCircle.getEllipse2DX());
						collection.add(newCircle);
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
						Lines newLine = new Lines(gList.item(i));
						
						if(newLine.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newLine.setStrokeColor(stroke);
						
						collection.add(newLine);
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
				}
			}
				
		}
	}

	public void setSize(String width, String height)
	{
		this.svgWidth = Units.setUnit(width);
		this.svgHeight = Units.setUnit(height);
	}
	
	public double getWidth()
	{
		return this.svgWidth;
	}
	
	public double getHeight()
	{
		return this.svgHeight;
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
	
	public LinkedHashSet<Drawings> getDrawings()
	{
		return this.drawingCollection;
	}
}
