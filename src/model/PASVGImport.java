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
	private LinkedHashSet<PAShape> svgShapeCollection;
	// private SVGTag svgElement;
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
	 * @param svgFile
	 *            the svgFile to set
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
	 * @param svgDoc
	 *            the svgDoc to set
	 */
	public void setSvgDoc(Document svgDoc) {
		this.svgDoc = svgDoc;
	}

	/**
	 * @return the svgShapeCollection
	 */
	public LinkedHashSet<PAShape> getSvgShapeCollection() {
		return svgShapeCollection;
	}

	/**
	 * @param svgShapeCollection
	 *            the svgShapeCollection to set
	 */
	public void setSvgShapeCollection(LinkedHashSet<PAShape> svgShapeCollection) {
		this.svgShapeCollection = svgShapeCollection;
	}

	/**
	 * @return the isSVG
	 */
	public boolean isSVG() {
		return isSVG;
	}

	/**
	 * @param isSVG
	 *            the isSVG to set
	 */
	public void setIsSVG(boolean isSVG) {
		this.isSVG = isSVG;
	}

	/**
	 * 
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

		setIsSVG((!Pattern
				.matches(
						"([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\-\\_\\=\\+\\{\\}\\[\\]\\;\\'\\,\\.\\`\\~])+(.svg|.xml)",
						splits[splits.length - 1])));
	}

	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * @param directory
	 *            the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/**
	 * @throws ParserConfigurationException
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

		svgShapeCollection = readSVGElements();

	}

	private LinkedHashSet<PAShape> readSVGElements() {
		// TODO Auto-generated method stub
		return null;
	}
}
