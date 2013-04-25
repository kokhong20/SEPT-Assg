package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import model.PACircle;
import model.PASVGContainer;
import model.PASVGElement;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    private int svgWidth;
    private int svgHeight;
    private double zoomScale;
    private double xPosition;
    private double yPosition;
    private BufferedImage svgImage;
    private PAMainFrame mainFrame;
    private PASVGContainer svgContainer;
    private LinkedList<PASVGElement> elementCollection;

    /**
     * constructor to define PASVGPanel for PAMainFrame
     *
     * @param mainFrame
     */
    public PASVGPanel(PASVGContainer svgContainer)
    {
        this.svgContainer = svgContainer;
        this.elementCollection = svgContainer.getSvgContainer();
        zoomScale = 1;
	xPosition = 0;
	yPosition = 0;
        initPanel();
        drawToImage();
    }

    /**
     * Initialise Svg Panel
     */
    private void initPanel()
    {
        svgWidth = (int) svgContainer.getSvgTag().getWidth();
        svgHeight = (int) svgContainer.getSvgTag().getHeight();
        Dimension size = new Dimension(svgWidth, svgHeight);

        setBackground(Color.white);
        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    private void drawToImage()
    {
        svgImage = new BufferedImage(svgWidth, svgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = svgImage.createGraphics();

        //for anti-aliasing for better output.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(0, 0, svgWidth, svgHeight);
        g2d.setPaint(new Color(255,255,255,255));
        
        if (!elementCollection.isEmpty())
        {
            float strokeWidth;
            double x, y, diameter, width, height;
            BasicStroke stroke;
            Iterator<PASVGElement> it = elementCollection.iterator();

            while (it.hasNext())
            {
                PASVGElement drawItem = it.next();
                if (drawItem instanceof PACircle)
                {
                    x = ((PACircle)drawItem).getCx() - ((PACircle)drawItem).getR() * zoomScale + xPosition;
                    y = ((PACircle)drawItem).getCy() - ((PACircle)drawItem).getR() * zoomScale + yPosition;
                    diameter = ((PACircle)drawItem).getR() * 2 * zoomScale;
                    strokeWidth = (float) ((PASVGElement)drawItem).getStrokeWidth();
                    stroke = new BasicStroke(strokeWidth * (float) zoomScale);
                    
                    // creating 2D Shapes object 
                    Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
                    g2d.setColor(((PASVGElement)drawItem).getFill());
                    g2d.fill(circle);
                    g2d.setColor(((PASVGElement)drawItem).getStroke());
                    g2d.setStroke(stroke);
                    g2d.draw(circle);
                }
                /*else if (drawItem instanceof Rectangles)
                {
                    // creating 2D Shapes object 
                    Rectangle2D.Double rectShape = new Rectangle2D.Double(((Rectangles) drawItem).getX() * zoomScale + xPosition,
                            ((Rectangles) drawItem).getY() * zoomScale + yPosition, ((Rectangles) drawItem).getWidth() * zoomScale, ((Rectangles) drawItem).getHeight() * zoomScale);

                    g2d.setColor(((Shapes) drawItem).getFill());
                    g2d.fill(rectShape);
                    g2d.setColor(((Drawings) drawItem).getStrokeColor());
                    g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth() * ((float) zoomScale)));
                    g2d.draw(rectShape);
                }
                else if (drawItem instanceof Lines)
                {
                    // creating 2D Shapes object 

                    Line2D.Double lineShape = new Line2D.Double(((Lines) drawItem).getX1() * zoomScale + xPosition,
                            ((Lines) drawItem).getY1() * zoomScale + yPosition, ((Lines) drawItem).getX2() * zoomScale + xPosition,
                            ((Lines) drawItem).getY2() * zoomScale + yPosition);

                    g2d.setColor(((Drawings) drawItem).getStrokeColor());
                    g2d.setStroke(new BasicStroke(((Drawings) drawItem).getStrokeWidth() * ((float) zoomScale)));
                    g2d.draw(lineShape);
                }*/
            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(svgImage, 0, 0, this);
    }

}
