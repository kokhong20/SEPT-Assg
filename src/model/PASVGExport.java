package model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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
	private PASVGContainer svgContainer;

	/**
	 * 
	 * @param container
	 * @param directory
	 * @throws ParserConfigurationException
	 */
	public PASVGExport(PASVGContainer container, String directory) throws ParserConfigurationException
	{
		// TODO Auto-generated constructor stub
		setDirectory(directory);
		createNewSVG();
		setSvgContainer(container);
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

		/**
		 * Reading through collection and append to svg not done yet
		 */
		
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
	 * @return the svgContainer
	 */
	public PASVGContainer getSvgContainer() {
		return svgContainer;
	}

	/**
	 * @param svgContainer the svgContainer to set
	 */
	public void setSvgContainer(PASVGContainer svgContainer) {
		this.svgContainer = svgContainer;
	}
}
