package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author bryantylai/LiHao
 */
public class PASVGImport
{
	/**
	 * 
	 * Process current SVG file by parsing the SVG file to be readable in JAVA
	 * 
	 * @param svgFile
	 *            file from file chooser
	 * @return svg's Document.
	 */
	private PASVGTag svgElement;
	private LinkedList<PAShape> shapesCollection = new LinkedList<PAShape>();
	private Document svgDoc;

	public PASVGImport(File svgFile)
	{
		processFiletoDoc(svgFile);
		readSVGElements(this.getSVGDoc());

	}

	public void processFiletoDoc(File svgFile)
	{
		Document svgDoc = null;
		DocumentBuilderFactory svgDBFactory = DocumentBuilderFactory
				.newInstance();

		try
		{
			svgDBFactory.setFeature("http://xml.org/sax/features/namespaces",
					false);
			svgDBFactory.setFeature("http://xml.org/sax/features/validation",
					false);
			svgDBFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
							false);
			svgDBFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);

			DocumentBuilder svgDocBuilder = svgDBFactory.newDocumentBuilder();
			svgDoc = svgDocBuilder.parse(svgFile);
			svgDoc.getDocumentElement().normalize();
		}
		catch (ParserConfigurationException | SAXException | IOException ex)
		{
			System.err.println(ex.getMessage());
		}

		this.setSVGDoc(svgDoc);
	}

	/**
	 * Read the current SVG file and add all available elements (rect, circle,
	 * line, g) into a LinkedList
	 * 
	 * @return a LinkedList of all shapes
	 */
	public void readSVGElements(Document svgDoc)
	{
		// TODO Auto-generated method stub
		if (svgDoc != null)
		{
			// Processing SVG tag
			Node svg = svgDoc.getElementsByTagName("svg").item(0);
			svgElement = new PASVGTag(svg);
			System.out.println(svg.DOCUMENT_POSITION_CONTAINS);

			NodeList drawList = svg.getChildNodes();
			for (int index = 0; index < drawList.getLength(); index++)
			{
				switch (drawList.item(index).getNodeName())
				{
				case "rect":
					PARectangle newRect = new PARectangle(drawList.item(index));
					newRect.readAttributes();

					// Add new rectangle object to Shapes and put into linked
					// list
					// Rectangle2D.Double rectShape = new
					// Rectangle2D.Double(newRect.getX(),newRect.getY(),newRect.getWidth(),newRect.getHeight());
					this.shapesCollection.add(newRect);
					break;

				case "circle":
					PACircle newCircle = new PACircle(drawList.item(index));
					newCircle.readAttributes();

					// Add new circle object to Shapes and put into linked List
					// Ellipse2D.Double circleShape = new
					// Ellipse2D.Double(newCircle.getEllipse2DX(),newCircle.getEllipse2DY(),newCircle.getR()*2,newCircle.getR()*2);

					this.shapesCollection.add(newCircle);
					break;

				case "line":
					PALine newLine = new PALine(drawList.item(index));
					newLine.readAttributes();

					this.shapesCollection.add(newLine);
					break;

				// case "g":
				// NodeList gList = drawList.item(index).getChildNodes();
				// createGroups(this.shapesCollection, gList,
				// drawList.item(index), null, null);
				//
				// break;
				}
			}
		}
		else if (svgDoc == null)
		{
			System.out.println("let me know");
		}

		/**
		 * Reading of svg elements not done yet
		 */
		this.setShapesCollection(shapesCollection);
	}

	public void setShapesCollection(LinkedList<PAShape> shapesCollection)
	{
		this.shapesCollection = shapesCollection;
	}

	public PASVGTag getSVGElement()
	{
		return svgElement;
	}

	public LinkedList<PAShape> getShapeCollection()
	{
		return this.shapesCollection;
	}

	public Document getSVGDoc()
	{
		return svgDoc;
	}

	public void setSVGDoc(Document svgDoc)
	{
		this.svgDoc = svgDoc;
	}

}
