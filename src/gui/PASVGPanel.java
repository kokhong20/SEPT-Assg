package gui;

import static controller.PADrawingShapeAction.DrawRectangleAction.endDrag;
import static controller.PADrawingShapeAction.DrawRectangleAction.startDrag;
import static gui.PADrawingItem.buttonSelected;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;
import model.PACircle;
import model.PALine;
import model.PASVGContainer;
import model.PASVGElement;
import model.PARectangle;
import model.PASVGGroup;

/**
 *
 * @author KokHong
 *
 */
public class PASVGPanel extends JPanel
{
    public BufferedImage svgImage;
    public PASVGContainer svgContainer;
    private int svgWidth;
    private int svgHeight;
    private double scale;
    private PAMainFrame mainFrame;
    private Rectangle2D handleRectangle;
    private LinkedList<PASVGElement> elementCollection;

    /**
     * constructor to define PASVGPanel for PAMainFrame
     *
     * @param mainFrame
     */
    public PASVGPanel(PASVGContainer svgContainer)
    {
        this.svgContainer = svgContainer;
        scale = 1.0;
        elementCollection = svgContainer.getSvgContainer();
        initialize();
        drawToImage();
    }
    
    public double getScale()
    {
        return scale;
    }
    
    public int getSVGWidth()
    {
        return svgWidth;
    }
    
    public int getSVGHeight()
    {
        return svgHeight;
    }
    
    public void zoomInOutSVG(double scale)
    {
        this.scale = scale;
        drawToImage();
        initialize();
        revalidate();
        repaint();
    }

    /**
     * Initialize SVG Panel.
     */
    private void initialize()
    {
        svgWidth = (int) svgContainer.getSvgTag().getWidth();
        svgHeight = (int) svgContainer.getSvgTag().getHeight();
        Dimension size = new Dimension((int) (svgWidth * scale), (int) (svgHeight * scale));

        setMaximumSize(size);
        setMinimumSize(size);
        setPreferredSize(size);
    }

    private void drawToImage()
    {
        svgImage = new BufferedImage((int) (svgWidth * scale), (int) (svgHeight * scale), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = svgImage.createGraphics();

        //for anti-aliasing for better output.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.scale(scale, scale);
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
                float strokeWidth = (float) ((PASVGElement) drawItem).getStrokeWidth();
                BasicStroke stroke = new BasicStroke(strokeWidth);

                if (drawItem instanceof PACircle)
                {
                    drawCircle(g2d, drawItem, stroke);
                }
                else if (drawItem instanceof PARectangle)
                {
                    drawRect(g2d, drawItem, stroke);
                }
                else if (drawItem instanceof PALine)
                {
                    drawLine(g2d, drawItem, stroke);
                }
                else if (drawItem instanceof PASVGGroup)
                {
                    LinkedList<PASVGElement> groupElement = ((PASVGGroup) drawItem).getGroupElementList();
                    iterateList(g2d, groupElement);
                }
            }
        }
    }

    private void drawCircle(Graphics2D g2d, PASVGElement drawItem, BasicStroke stroke)
    {
        // creating 2D Shapes object 
        Ellipse2D.Double circle = ((PACircle) drawItem).getEllipse2D();
        g2d.setColor(((PASVGElement) drawItem).getFill());
        g2d.fill(circle);
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(circle);
    }

    private void drawRect(Graphics2D g2d, PASVGElement drawItem, BasicStroke stroke)
    {
        // creating 2D Shapes object 
        Rectangle2D.Double rect = ((PARectangle) drawItem).getRectangle2D();
        g2d.setColor(((PASVGElement) drawItem).getFill());
        g2d.fill(rect);
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(rect);
    }

    private void drawLine(Graphics2D g2d, PASVGElement drawItem, BasicStroke stroke)
    {
        // creating 2D Shapes object 
        Line2D.Double line = ((PALine) drawItem).getLine2D();
        g2d.setColor(((PASVGElement) drawItem).getStroke());
        g2d.setStroke(stroke);
        g2d.draw(line);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(svgImage, 0, 0, this);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (startDrag != null && endDrag != null)
        {
            switch (buttonSelected.getToolTipText())
            {

                case "Rectangle":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    Rectangle2D.Double rect = makeRectangle(startDrag.x,
                            startDrag.y, endDrag.x, endDrag.y);
                    g2d.draw(rect);
                    break;

                case "Line":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    g2d.setStroke(new BasicStroke(2));
                    Line2D.Double line = makeLine(startDrag.x, startDrag.y,
                            endDrag.x, endDrag.y);
                    g2d.draw(line);
                    break;

                case "Circle":
                    g2d.setPaint(Color.LIGHT_GRAY);
                    Ellipse2D.Double circle = makeCircle(startDrag.x, startDrag.y,
                            endDrag.x, endDrag.y);
                    g2d.draw(circle);
                    break;

            }
        }
    }

    private Rectangle2D.Double makeRectangle(int x1, int y1, int x2, int y2)
    {
        return new Rectangle2D.Double(Math.min(x1, x2), Math.min(y1, y2),
                Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Line2D.Double makeLine(int x1, int y1, int x2, int y2)
    {
        return new Line2D.Double(x1, y1, x2, y2);
    }

    private Ellipse2D.Double makeCircle(int x1, int y1, int x2, int y2)
    {
        double x = x2 < x1 ? x2 : x1;
        double y = y2 < y1 ? y2 : y1;

        double width = (Math.abs(x1 - x2) > Math.abs(y1 - y2) ? Math.abs(x1
                - x2) : Math.abs(y1 - y2));
        return new Ellipse2D.Double(x, y, width, width);
    }
    
}
