package model;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author bryantylai
 * 
 */
public class PASVGExport {
	private Document svgDoc;
	private DocumentBuilderFactory svgDBFactory;
	private DocumentBuilder svgDocBuilder;
	private String directory;
	private LinkedHashSet<PAShape> svgShapeCollection;
	private PASVGTag svgTag;

	// Should be passing in PASVGContainer instead of a collection of shapeCollection but what the heck.. Modify after PASVGContainer is completed.
	/**
	 *  
	 * @param svgTag
	 * @param shapeCollection
	 * @param directory
	 * @throws ParserConfigurationException
	 * @throws TransformerConfigurationException
	 */
	public PASVGExport(PASVGTag svgTag, LinkedHashSet<PAShape> shapeCollection, String directory) throws ParserConfigurationException, TransformerConfigurationException {
		// TODO Auto-generated constructor stub
		setDirectory(directory);
		createNewSVG();
		setSvgTag(svgTag);
		setSvgShapeCollection(shapeCollection);
	}

	/**
	 * Create a new SVG file using DocumentBuilder
	 * 
	 * @throws ParserConfigurationException
	 */
	public void createNewSVG() throws ParserConfigurationException {
		// TODO Auto-generated method stub
		svgDBFactory = DocumentBuilderFactory.newInstance();
		svgDocBuilder = svgDBFactory.newDocumentBuilder();
		setSvgDoc(svgDocBuilder.newDocument());		
	}
	
	// For testing or show only, modify when PASVGContainer is completed
	/**
	 * Saving current SVG to a SVG file
	 * Loops through all PAShape and append to SVG file
	 * 
	 * @throws TransformerException
	 */
	public void saveToSVG() throws TransformerException{
		
		Element svg = svgDoc.createElement("svg");
		svgDoc.appendChild(svg);

		Iterator<PAShape> it = svgShapeCollection.iterator();

		while (it.hasNext()) {
			PAShape draw = it.next();
			
			if(draw instanceof PARectangle)
			{
				PARectangle saveRect = (PARectangle) draw;				
				Element rect = svgDoc.createElement("rect");
				rect.setAttribute("width", String.valueOf(saveRect.getWidth()));
				rect.setAttribute("height", String.valueOf(saveRect.getHeight()));
				rect.setAttribute("x", String.valueOf(saveRect.getX()));
				rect.setAttribute("y", String.valueOf(saveRect.getY()));
				rect.setAttribute("fill", String.valueOf("rgb(" + saveRect.getFill().getRed()
								+ "," + saveRect.getFill().getGreen() + ","
								+ saveRect.getFill().getBlue())
								+ ")");
				rect.setAttribute("stroke", String.valueOf("rgb(" + saveRect.getStroke().getRed()
								+ "," + saveRect.getStroke().getGreen() + ","
								+ saveRect.getStroke().getBlue())
								+ ")");
				rect.setAttribute("stroke-width", String.valueOf(saveRect.getStrokeWidth()));
				svg.appendChild(rect);
			}
			else if(draw instanceof PACircle)
			{
				PACircle saveCircle = (PACircle) draw;
				Element circle = svgDoc.createElement("circle");
				circle.setAttribute("cx", String.valueOf(saveCircle.getCx()));
				circle.setAttribute("cy", String.valueOf(saveCircle.getCy()));
				circle.setAttribute("r", String.valueOf(saveCircle.getR()));
				circle.setAttribute("fill", String.valueOf("rgb(" + saveCircle.getFill().getRed()
								+ "," + saveCircle.getFill().getGreen() + ","
								+ saveCircle.getFill().getBlue())
								+ ")");
				circle.setAttribute("stroke", String.valueOf("rgb(" + saveCircle.getStroke().getRed()
								+ "," + saveCircle.getStroke().getGreen() + ","
								+ saveCircle.getStroke().getBlue())
								+ ")");
				circle.setAttribute("stroke-width", String.valueOf(saveCircle.getStrokeWidth()));
				svg.appendChild(circle);
			}
				else if(draw instanceof PALine)
			{
					PALine saveLine = (PALine) draw;
				Element line = svgDoc.createElement("line");
				line.setAttribute("x1", String.valueOf(saveLine.getX1()));
				line.setAttribute("x2", String.valueOf(saveLine.getX2()));
				line.setAttribute("y1", String.valueOf(saveLine.getY1()));
				line.setAttribute("y2", String.valueOf(saveLine.getY2()));
				line.setAttribute("stroke", String.valueOf("rgb(" + saveLine.getStroke().getRed()
								+ "," + saveLine.getStroke().getGreen() + ","
								+ saveLine.getStroke().getBlue())
								+ ")");
				line.setAttribute("stroke-width", String.valueOf(saveLine.getStrokeWidth()));
				svg.appendChild(line);
			}
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(svgDoc);

		StreamResult result =  new StreamResult(new File(getDirectory()));
		transformer.transform(source, result);
	}

	/**
	 * @return the svgDoc
	 */
	public Document getSvgDoc() {
		return svgDoc;
	}

	/**
	 * @param svgDoc the svgDoc to set
	 */
	public void setSvgDoc(Document svgDoc) {
		this.svgDoc = svgDoc;
	}

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	/**
	 * @return the svgShapeCollection
	 */
	public LinkedHashSet<PAShape> getSvgShapeCollection() {
		return svgShapeCollection;
	}

	/**
	 * @param svgShapeCollection the svgShapeCollection to set
	 */
	public void setSvgShapeCollection(LinkedHashSet<PAShape> svgShapeCollection) {
		this.svgShapeCollection = svgShapeCollection;
	}

	/**
	 * @return the svgTag
	 */
	public PASVGTag getSvgTag() {
		return svgTag;
	}

	/**
	 * @param svgTag the svgTag to set
	 */
	public void setSvgTag(PASVGTag svgTag) {
		this.svgTag = svgTag;
	}
}
