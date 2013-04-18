package model;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author bryantylai/LiHao
 */
public class PASVGImport
{
    public static Document processFiletoDoc(File svgFile)
    {
        Document svgDoc = null;
        DocumentBuilderFactory svgDBFactory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder svgDocBuilder = svgDBFactory.newDocumentBuilder();
            svgDoc = svgDocBuilder.parse(svgFile);
            svgDoc.getDocumentElement().normalize();
        }
        catch (ParserConfigurationException | SAXException | IOException ex)
        {
            System.err.println(ex.getMessage());
        }

        return svgDoc;
    }

    /**
     * Read the current SVG file and add all available elements (rect, circle,
     * line, g) into a LinkedList
     *
     * @return a LinkedList of all shapes
     */
    public static LinkedList<PAShape> readSVGElements(Document svgDoc)
    {
        // TODO Auto-generated method stub
        LinkedList<PAShape> shapesCollection = new LinkedList<PAShape>();

        /**
         * Reading of svg elements not done yet
         */
        return shapesCollection;
    }

}
