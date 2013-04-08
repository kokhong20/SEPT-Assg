package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author bryantylai
 * 
 */
public class PASVGImport {
	private File svgFile;
	private Document svgDoc;
	private DocumentBuilderFactory svgDBFactory;
	private DocumentBuilder svgDocBuilder;
	private boolean isSVG;
	private String directory;

	/**
	 * 
	 * @param directory
	 */
	public PASVGImport(String directory) {
		setDirectory(directory);
		validateSVG(getDirectory());
	}

	/**
	 * @return the svgFile
	 */
	public File getSvgFile() {
		return svgFile;
	}

	/**
	 * @param svgFile the svgFile to set
	 */
	public void setSvgFile(File svgFile) {
		this.svgFile = svgFile;
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
	 * @return the isSVG
	 */
	public boolean isSVG() {
		return isSVG;
	}

	/**
	 * @param isSVG the isSVG to set
	 */
	public void setIsSVG(boolean isSVG) {
		this.isSVG = isSVG;
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
	 * Validate the current file whether it
	 * is a valid SVG file to be opened
	 * @param directory
	 */
	private void validateSVG(String directory) {
		// TODO Auto-generated method stub
		String dir = directory.toLowerCase();

		String nameOS = "os.name";
		String[] splits;

		if (System.getProperty(nameOS).equals("Mac OS X")) {
			splits = dir.split("/");
		}

		else {
			splits = dir.split("\\\\");
		}

		setIsSVG((!Pattern.matches("([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\-\\_\\=\\+\\{\\}\\[\\]\\;\\'\\,\\.\\`\\~])+(.svg|.xml)",splits[splits.length - 1])));
	}
	
	/**
	 * Process current SVG file by parsing the SVG file
	 * to be readable in JAVA
	 * 
	 * @throws ParserConfigurationException if a DocumentBuilder cannot be created which satisfies the configuration requested.
	 * @throws IOException
	 * @throws SAXException
	 * 
	 */
	private void processSVG() throws ParserConfigurationException,
			SAXException, IOException {
		// TODO Auto-generated method stub
		setSvgFile(new File(getDirectory()));
		svgDBFactory = DocumentBuilderFactory.newInstance();
		svgDocBuilder = svgDBFactory.newDocumentBuilder();

		svgDoc = svgDocBuilder.parse(getSvgFile());
		svgDoc.getDocumentElement().normalize();
	}
	
	/**
	 * Create a new PASVGTag by passing the SVG Element
	 * to the constructor of PASVGTag 
	 * @return a new PASVGTag object
	 */
	private PASVGTag createSVGTag()
	{
		return new PASVGTag(svgDoc.getElementsByTagName("svg").item(0));
	}
	
	/**
	 * Read the current SVG file and add all available elements
	 * (rect, circle, line, g) into a LinkedHashSet
	 * @return a LinkedHashSet of all shapes 
	 */
	private LinkedHashSet<PAShape> readSVGElements() {
		// TODO Auto-generated method stub
		LinkedHashSet<PAShape> shapesCollection = new LinkedHashSet<PAShape>();
		
		return shapesCollection;
	}
}
