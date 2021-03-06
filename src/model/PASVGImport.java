package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author bryantylai/LiHao/Lai Sai Hoo
 * @since 1.0
 * @version 1.1
 * <p>This class reads and validate a file then converts it to be readable in JAVA.
 * SVG elements are then stored into a list</p>
 */
public class PASVGImport
{
    /**
     * 
     * Process current SVG file by parsing the SVG file to be readable in JAVA
     * 
     * @param svgFile file from file chooser
     * @return svg's Document.
     */
    public static Document processFiletoDoc(File svgFile)
    {
        Document svgDoc = null;
        DocumentBuilderFactory svgDBFactory = DocumentBuilderFactory
                .newInstance();
        if (!Pattern
                .matches(
                        "([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\-\\_\\=\\+\\{\\}\\[\\]\\;\\'\\,\\.\\`\\~])+(.svg|.xml)",
                        svgFile.getName()))
        {
            JOptionPane.showMessageDialog(null, "The file is not a SVG file.",
                    "Not SVG File", JOptionPane.WARNING_MESSAGE);
            svgDoc = null;

        }
        else
        {
            try
            {
                svgDBFactory.setFeature("http://xml.org/sax/features/namespaces", false);
                svgDBFactory.setFeature("http://xml.org/sax/features/validation", false);
                svgDBFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
                svgDBFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

                DocumentBuilder svgDocBuilder = svgDBFactory.newDocumentBuilder();
                svgDoc = svgDocBuilder.parse(svgFile);
                svgDoc.getDocumentElement().normalize();
            }
            catch (FileNotFoundException fnf)
            {
                JOptionPane.showMessageDialog(null, "File Not Found, Please check the file's directory is correct.",
                                "File Not Found", JOptionPane.WARNING_MESSAGE);
                svgDoc = null;
            }
            catch (ParserConfigurationException | SAXException | IOException ex)
            {
                System.err.println(ex.getMessage());
            }
        }
        return svgDoc;
    }

    /**
     * Read the current SVG file and add all available elements (rect, circle,
     * line, g) into a LinkedList
     * 
     * @return a LinkedList of all shapes
     */
    public static LinkedList<PASVGElement> readSVGElements(Document svgDoc)
    {

        LinkedList<PASVGElement> elementCollection = new LinkedList<>();
        // TODO Auto-generated method stub
        if (svgDoc != null)
        {
            // Processing SVG tag
            Node svg = svgDoc.getElementsByTagName("svg").item(0);
            NodeList drawList = svg.getChildNodes();

            for (int index = 0; index < drawList.getLength(); index++)
            {
                switch (drawList.item(index).getNodeName())
                {
                case "rect":
                    PARectangle newRect = new PARectangle(drawList.item(index));
                    newRect.readAttributes();
                    
                    elementCollection.add(newRect);
                    break;

                case "circle":
                    PACircle newCircle = new PACircle(drawList.item(index));
                    newCircle.readAttributes();

                    elementCollection.add(newCircle);
                    break;

                case "line":
                    PALine newLine = new PALine(drawList.item(index));
                    newLine.readAttributes();

                    elementCollection.add(newLine);
                    break;

                case "g":
                    PASVGGroup newGroup = new PASVGGroup(drawList.item(index));
                    newGroup.readAttributes();

                    elementCollection.add(newGroup);
                    break;
                }
            }
        }
        return elementCollection;
    }

}
