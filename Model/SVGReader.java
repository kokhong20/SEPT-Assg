package Model;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

public class SVGReader 
{
	private File fXmlFile;
	private Document doc;
	private DocumentBuilderFactory dbFactory;
	private LinkedHashSet <Drawings> drawingCollection;
	private SVGTag svgElement;
	private boolean errorDetect; 

	public SVGReader()
	{
		doc = null;
		fXmlFile = null;
		dbFactory = DocumentBuilderFactory.newInstance();
		this.drawingCollection = new LinkedHashSet<Drawings>();
		this.errorDetect = true;
	}

	// set Document with dir from MenuAction.
	public void setDoc(String dir)
	{
		dir = dir.toLowerCase();
		String nameOS = "os.name";
		String[] splits;
		
		if (System.getProperty(nameOS).equals("Mac OS X"))
		{
			splits = dir.split("/");
		}
		
		else
		{
			splits = dir.split("\\\\");
		}
			
		try
		{
			if (Pattern.matches("([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\-\\_\\=\\+\\{\\}\\[\\]\\;\\'\\,\\.\\`\\~])+(.svg|.xml)", splits[splits.length - 1]))
			{
				JOptionPane.showMessageDialog(null, "The file is not a SVG file.", "Not SVG File", JOptionPane.WARNING_MESSAGE);
				this.errorDetect = false;
			}
			
			else
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
				
				this.processFile();
			}
		}
		
		catch(FileNotFoundException fNFE)
		{
			JOptionPane.showMessageDialog(null, "File Not Found, Please check the file's directory is correct.", "File Not Found", JOptionPane.WARNING_MESSAGE);
			this.errorDetect = false;
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
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
			//Processing SVG tag
			Node svg = doc.getElementsByTagName("svg").item(0);
			svgElement = new SVGTag(svg);
			svgElement.setSVGAttr();
			
			NodeList drawList = svg.getChildNodes();
			for (int index = 0; index < drawList.getLength(); index++)
			{
				switch(drawList.item(index).getNodeName())
				{
					case "rect":
						Rectangles newRect = new Rectangles(drawList.item(index));
						newRect.readAttributes();

						//Add new rectangle object to Shapes and put into linked list
						//Rectangle2D.Double rectShape = new Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
						this.drawingCollection.add(newRect);
						break;

					case "circle":
						Circles newCircle = new Circles(drawList.item(index));
						newCircle.readAttributes();

						//Add new circle object to Shapes and put into linked List
						//Ellipse2D.Double circleShape = new Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);
						
						this.drawingCollection.add(newCircle);
						break;

					case "line":
						Lines newLine = new Lines(drawList.item(index));
						newLine.readAttributes();

						this.drawingCollection.add(newLine);
						break;

					case "g":
						NodeList gList = drawList.item(index).getChildNodes();
						createGroups(this.drawingCollection, gList, drawList.item(index), null, null);
						
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
						newRect.readAttributes();
						//Add new rectangle object to Shapes and put into linked list
						//Rectangle2D.Double rectShape = new Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
						if(newRect.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newRect.setStrokeColor(stroke);

						if(newRect.getFill().equals(Color.BLACK) && fill != null)
							newRect.setFill(fill);

						collection.add(newRect);
						break;
					case "circle":
						Circles newCircle = new Circles(gList.item(i));
						newCircle.readAttributes();
						//Add new circle object to Shapes and put into linked List
						//Ellipse2D.Double circleShape = new Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);
						if(newCircle.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newCircle.setStrokeColor(stroke);

						if(newCircle.getFill().equals(Color.BLACK) && fill != null)
							newCircle.setFill(fill);

						System.out.println("x is"+newCircle.getEllipse2DX());
						collection.add(newCircle);
						break;
					case "line":
						Lines newLine = new Lines(gList.item(i));
						newLine.readAttributes();
						
						if(newLine.getStrokeColor().equals(Color.BLACK) && stroke != null)
							newLine.setStrokeColor(stroke);

						collection.add(newLine);
						break;
				}
			}

		}
	}
	
	public SVGTag getSVGElement()
	{
		return svgElement;
	}

	public LinkedHashSet<Drawings> getDrawings()
	{
		return this.drawingCollection;
	}
	
	public boolean getError()
	{
		return this.errorDetect;
	}
}
