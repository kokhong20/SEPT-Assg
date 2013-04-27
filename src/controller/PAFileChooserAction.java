/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PADrawingKit;
import gui.PAMainFrame;
import gui.PAStartMenu;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import model.PACircle;
import model.PALine;
import model.PARectangle;
import model.PASVGContainer;
import model.PASVGElement;
import model.PASVGGroup;
import model.PASVGImport;
import model.PASVGTag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author LiHao
 */
public class PAFileChooserAction implements ActionListener
{
    private int svgWidth;
    private int svgHeight;
    public BufferedImage svgImage;
    private JDesktopPane parent;
    private JFileChooser fileChooser;
    private JInternalFrame frame;
    private PASVGImport svgImport;
    private PAStartMenu startMenu;
    private LinkedList<PASVGElement> elementCollection;

    /**
     *
     * Contructor for file chooser's action.
     *
     * @param fileChooser pass existing file chooser.
     * @param frame internal frame which store file chooser.
     */
    public PAFileChooserAction(JDesktopPane parent, JFileChooser fileChooser, JInternalFrame frame)
    {
        this.parent = parent;
        this.fileChooser = fileChooser;
        this.frame = frame;
    }

    public PAFileChooserAction(JDesktopPane parent, PAStartMenu startMenu, JFileChooser fileChooser, JInternalFrame frame)
    {
        this.parent = parent;
        this.fileChooser = fileChooser;
        this.frame = frame;
        this.startMenu = startMenu;
    }

    private void drawToImage()
    {
        svgImage = new BufferedImage(svgWidth, svgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = svgImage.createGraphics();

        //for anti-aliasing for better output.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(0, 0, svgWidth, svgHeight);
        g2d.setPaint(new Color(255, 255, 255, 255));
        iterateList(g2d, elementCollection);
    }

    private void iterateList(Graphics2D g2d, LinkedList<PASVGElement> collection)
    {
        if (!collection.isEmpty())
        {
            Iterator<PASVGElement> it = collection.iterator();

            while (it.hasNext())
            {
                PASVGElement drawItem = it.next();

                if (drawItem instanceof PACircle)
                {
                    drawCircle(g2d, drawItem);
                }
                else if (drawItem instanceof PARectangle)
                {
                    drawRect(g2d, drawItem);
                }
                else if (drawItem instanceof PALine)
                {
                    drawLine(g2d, drawItem);
                }
                else if (drawItem instanceof PASVGGroup)
                {
                    LinkedList<PASVGElement> groupElement = ((PASVGGroup) drawItem).getGroupElementList();
                    iterateList(g2d, groupElement);
                }
            }
        }
    }

    private void drawCircle(Graphics2D g2d, PASVGElement drawItem)
    {
        double x = ((PACircle) drawItem).getCx() - ((PACircle) drawItem).getR();
        double y = ((PACircle) drawItem).getCy() - ((PACircle) drawItem).getR();
        double diameter = ((PACircle) drawItem).getR() * 2;
        float strokeWidth = (float) ((PASVGElement) drawItem).getStrokeWidth();
        BasicStroke stroke = new BasicStroke(strokeWidth);

        // creating 2D Shapes object 
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g2d.setColor(((PASVGElement) drawItem).getFill());
        g2d.fill(circle);
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(circle);
    }

    private void drawRect(Graphics2D g2d, PASVGElement drawItem)
    {
        double x = ((PARectangle) drawItem).getX();
        double y = ((PARectangle) drawItem).getY();
        double shapeWidth = ((PARectangle) drawItem).getWidth();
        double shapeHeight = ((PARectangle) drawItem).getHeight();
        float strokeWidth = (float) ((PASVGElement) drawItem).getStrokeWidth();
        BasicStroke stroke = new BasicStroke(strokeWidth);

        // creating 2D Shapes object 
        Rectangle2D.Double rect = new Rectangle2D.Double(x, y, shapeWidth, shapeHeight);
        g2d.setColor(((PASVGElement) drawItem).getFill());
        g2d.fill(rect);
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(rect);
    }

    private void drawLine(Graphics2D g2d, PASVGElement drawItem)
    {
        double x = ((PALine) drawItem).getX1();
        double x2 = ((PALine) drawItem).getX2();
        double y = ((PALine) drawItem).getY1();
        double y2 = ((PALine) drawItem).getY2();
        float strokeWidth = (float) ((PASVGElement) drawItem).getStrokeWidth();
        BasicStroke stroke = new BasicStroke(strokeWidth);

        // creating 2D Shapes object 
        Line2D.Double line = new Line2D.Double(x, y, x2, y2);
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(line);
    }

    /**
     * action in file chooser (Cancel and Open button)
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Open Button Action
        if (!e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
        {
            File selectedFile = fileChooser.getSelectedFile();
            Document svgDoc = PASVGImport.processFiletoDoc(selectedFile);
            Node svgNode = svgDoc.getElementsByTagName("svg").item(0);
            PASVGTag svgTag = new PASVGTag(svgNode);
            svgWidth = (int) svgTag.getWidth();
            svgHeight = (int) svgTag.getHeight();
            elementCollection = PASVGImport.readSVGElements(svgDoc);
            drawToImage();
            
            PASVGContainer svgContainer = new PASVGContainer(svgTag, elementCollection);
            PAMainFrame svgDisplay = new PAMainFrame(parent, svgContainer, svgImage);
            PADrawingKit drawingKit = new PADrawingKit(svgDisplay);
            parent.add(svgDisplay);
            parent.add(drawingKit);

            if (startMenu != null)
            {
                startMenu.setVisible(false);
                startMenu.dispose();
            }
           
        }

        // need to do for both Cancel and Open button.
        frame.setVisible(false);
        frame.dispose();
    }

}
