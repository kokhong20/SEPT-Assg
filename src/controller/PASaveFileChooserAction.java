package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.PACircle;
import model.PALine;
import model.PARectangle;
import model.PASVGContainer;
import model.PASVGElement;
import model.PASVGGroup;
import model.PASVGTag;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author LiHao
 * @since 1.1
 *
 * <p>
 * This class creates a PASaveFileChooserAction to set action performed for
 * JFileChooser button and method to save file.
 * </p>
 *
 */
public class PASaveFileChooserAction implements ActionListener
{
    private JFileChooser fileChooser;
    private JInternalFrame frame;
    private PASVGContainer svgContainer;

    /**
     *
     * Create a new PASaveFileChooserAction which accept JDeskropPane as parent
     * to show JInternalFrame, JFileChooser want to perform action,
     * JInternalFrame on focus and PASVGContainer to process save action.
     *
     * @param parent set PASaveFileChooserAction parent
     * @param fileChooser set JFileChooser want to perform action
     * @param frame set JInternalFrame want to dispose
     * @param svgContainer set PASVGContainer want to process save action.
     */
    public PASaveFileChooserAction(JDesktopPane parent, JFileChooser fileChooser, JInternalFrame frame, PASVGContainer svgContainer)
    {
        this.fileChooser = fileChooser;
        this.frame = frame;
        this.svgContainer = svgContainer;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Open Button Action
        if (!e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
        {
            File selectedFile = fileChooser.getSelectedFile();
            saveToFile(selectedFile, svgContainer);
        }

        frame.setVisible(false);
        frame.dispose();
        PAMenuAction.SaveAsFile.fcInternal = null;
    }

    private void iterateList(Document doc, Element ele, LinkedList<PASVGElement> elements)
    {
        // TODO Auto-generated method stub
        Iterator<PASVGElement> iterator = elements.iterator();
        while (iterator.hasNext())
        {
            PASVGElement element = iterator.next();

            if (element instanceof PARectangle)
            {
                PARectangle saveRect = (PARectangle) element;

                Element rect = doc.createElement("rect");
                rect.setAttribute("width", String.valueOf(saveRect.getWidth()));
                rect.setAttribute("height", String.valueOf(saveRect.getHeight()));
                rect.setAttribute("x", String.valueOf(saveRect.getX()));
                rect.setAttribute("y", String.valueOf(saveRect.getY()));
                rect.setAttribute("fill", saveRect.getFill().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveRect.getFill().getRed() + "," + saveRect.getFill().getGreen() + "," + saveRect.getFill().getBlue()) + ")");
                rect.setAttribute("stroke", saveRect.getStroke().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveRect.getStroke().getRed() + "," + saveRect.getStroke().getGreen() + "," + saveRect.getStroke().getBlue()) + ")");
                rect.setAttribute("stroke-width", String.valueOf(saveRect.getStrokeWidth()));

                ele.appendChild(rect);
            }
            else if (element instanceof PACircle)
            {
                PACircle saveCircle = (PACircle) element;

                Element circle = doc.createElement("circle");
                circle.setAttribute("cx", String.valueOf(saveCircle.getCx()));
                circle.setAttribute("cy", String.valueOf(saveCircle.getCy()));
                circle.setAttribute("r", String.valueOf(saveCircle.getR()));
                circle.setAttribute("fill", saveCircle.getFill().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveCircle.getFill().getRed() + "," + saveCircle.getFill().getGreen() + "," + saveCircle.getFill().getBlue()) + ")");
                circle.setAttribute("stroke", saveCircle.getStroke().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveCircle.getStroke().getRed() + "," + saveCircle.getStroke().getGreen() + "," + saveCircle.getStroke().getBlue()) + ")");
                circle.setAttribute("stroke-width", String.valueOf(saveCircle.getStrokeWidth()));

                ele.appendChild(circle);
            }
            else if (element instanceof PALine)
            {
                PALine saveLine = (PALine) element;

                Element line = doc.createElement("line");
                line.setAttribute("x1", String.valueOf(saveLine.getX1()));
                line.setAttribute("x2", String.valueOf(saveLine.getX2()));
                line.setAttribute("y1", String.valueOf(saveLine.getY1()));
                line.setAttribute("y2", String.valueOf(saveLine.getY2()));
                line.setAttribute("stroke", saveLine.getStroke().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveLine.getStroke().getRed() + "," + saveLine.getStroke().getGreen() + "," + saveLine.getStroke().getBlue()) + ")");
                line.setAttribute("stroke-width", String.valueOf(saveLine.getStrokeWidth()));

                ele.appendChild(line);
            }
            else if (element instanceof PASVGGroup)
            {
                PASVGGroup saveGroup = (PASVGGroup) element;

                Element group = doc.createElement("g");

                if (saveGroup.getFill() != null)
                {
                    group.setAttribute("fill", saveGroup.getFill().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveGroup.getFill().getRed() + "," + saveGroup.getFill().getGreen() + "," + saveGroup.getFill().getBlue()) + ")");
                }

                if (saveGroup.getStroke() != null)
                {
                    group.setAttribute("stroke", saveGroup.getStroke().getAlpha() == 0 ? "none" : String.valueOf("rgb(" + saveGroup.getStroke().getRed() + "," + saveGroup.getStroke().getGreen() + "," + saveGroup.getStroke().getBlue()) + ")");
                }

                if (saveGroup.getStrokeWidth() != 0.0)
                {
                    group.setAttribute("stroke-width", String.valueOf(saveGroup.getStrokeWidth()));
                }

                LinkedList<PASVGElement> groupElements = saveGroup.getGroupElementList();

                iterateList(doc, group, groupElements);

                ele.appendChild(group);
            }
        }
    }

    /**
     *
     * This method is used to save a output file which accept File to be updated
     * and PASVGContainer to convert to .svg File.
     *
     * @param file set file to update or create
     * @param svgContainer set PASVGContainer want to process save action.
     */
    public void saveToFile(File file, PASVGContainer svgContainer)
    {
        // TODO Auto-generated method stub

        PASVGTag svgTag = svgContainer.getSvgTag();
        LinkedList<PASVGElement> elements = svgContainer.getSvgContainer();

        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            Element svg = doc.createElementNS("http://www.w3.org/2000/svg", "svg");
            svg.setAttribute("width", String.valueOf(svgTag.getWidth()));
            svg.setAttribute("height", String.valueOf(svgTag.getHeight()));
            svg.setAttribute("fill", String.valueOf(svgTag.getFill()));
            svg.setAttribute("stroke", String.valueOf(svgTag.getStroke()));
            svg.setAttribute("stroke-width", String.valueOf(svgTag.getStrokeWidth()));
            doc.appendChild(svg);

            iterateList(doc, svg, elements);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = null;
            if (file.getPath().contains(".svg"))
            {
                result = new StreamResult(file);
            }
            else
            {
                result = new StreamResult(file + ".svg");
            }
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
        }
        catch (ParserConfigurationException | TransformerException ex)
        {
            // TODO Auto-generated catch block
            System.err.println(ex.getMessage());
        }
    }

}
