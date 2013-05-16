package model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 * @since 5 April 2013
 * <p>This class creates a new PARectangle as a PASVGElement</p>
 */
public class PARectangle extends PASVGElement
{
    private double x;
    private double y;
    private double width;
    private double height;
    private Rectangle2D.Double rectangle2D;
    private Rectangle2D.Double[] rectHandleArray;

    /**
     * Creates an empty PARectangle
     */
    public PARectangle()
    {
    }

    /**
     * Creates a PARectangle based on attributes given when drawing a new
     * rectangle
     *
     * @param fill the color fill
     * @param stroke the color stroke
     * @param strokeWidth the stroke width
     * @param x the x coordinate
     * @param y the y coordinate
     * @param width the width
     * @param height the height
     */
    public PARectangle(Color fill, Color stroke, double strokeWidth, double x, double y, double width, double height)
    {
        super(fill, stroke, strokeWidth);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setRectangle2D();
    }

    /**
     * Creates a PARectangle which receives a node
     *
     * @param node an Element node
     */
    public PARectangle(Node node)
    {
        // TODO Auto-generated constructor stub
        super(node);
    }

    /**
     * Creates a PARectangle which receives a node and groupFill, groupStroke,
     * groupWidth from parent group
     *
     * @param node an Element node
     * @param groupFill fill of its group
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
     */
    public PARectangle(Node node, Color groupFill, Color groupStroke,
            double groupWidth, PASVGGroup parentGroup)
    {
        // TODO Auto-generated constructor stub
        super(node, groupFill, groupStroke, groupWidth, parentGroup);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readAttributes()
    {
        // TODO Auto-generated method stub
        if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
        {
            Element eNode = (Element) this.getNode();

            this.setX(PAUnit.setUnit(eNode.getAttribute("x"), DEFAULT_LENGTH));
            this.setY(PAUnit.setUnit(eNode.getAttribute("y"), DEFAULT_LENGTH));
            this.setWidth(PAUnit.setUnit(eNode.getAttribute("width"), DEFAULT_LENGTH));
            this.setHeight(PAUnit.setUnit(eNode.getAttribute("height"), DEFAULT_LENGTH));
            setRectangle2D();
        }
    }

    /**
     * @return the x
     */
    public double getX()
    {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY()
    {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * @return the width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * @return the rectangle2D
     */
    public Rectangle2D.Double getRectangle2D()
    {
        return rectangle2D;
    }

    /**
     * The Rectangle2D.Double to set
     */
    public final void setRectangle2D()
    {
        rectangle2D = new Rectangle2D.Double(x, y, width, height);
    }

    /**
     * Drawing of rect handles based on the scale
     *
     * @param scale the current scale
     * @return array of handles
     */
    private Rectangle2D.Double[] createRectHandle(double scale)
    {
        Rectangle2D rect = this.rectangle2D.getBounds2D();
        double xPoint = (rect.getX() - strokeWidth / 2) * scale;
        double yPoint = (rect.getY() - strokeWidth / 2) * scale;
        double w = (rect.getWidth() + strokeWidth) * scale;
        double h = (rect.getHeight() + strokeWidth) * scale;

        Rectangle2D.Double NW = new Rectangle2D.Double(xPoint - 3.0, yPoint - 3.0, 6.0,
                6.0);


        Rectangle2D.Double NE = new Rectangle2D.Double(xPoint + w - 3.0, yPoint - 3.0,
                6.0, 6.0);


        Rectangle2D.Double SW = new Rectangle2D.Double(xPoint - 3.0, yPoint + h - 3.0,
                6.0, 6.0);


        Rectangle2D.Double SE = new Rectangle2D.Double(xPoint + w - 3.0, yPoint + h - 3.0,
                6.0, 6.0);


        Rectangle2D.Double N = new Rectangle2D.Double(xPoint + (w / 2) - 3.0, yPoint - 3.0, 6.0,
                6.0);


        Rectangle2D.Double W = new Rectangle2D.Double(xPoint - 3.0, yPoint + (h / 2) - 3.0,
                6.0, 6.0);


        Rectangle2D.Double E = new Rectangle2D.Double(xPoint + w - 3.0, yPoint + (h / 2) - 3.0,
                6.0, 6.0);


        Rectangle2D.Double S = new Rectangle2D.Double(xPoint + (w / 2) - 3.0, yPoint + h - 3.0,
                6.0, 6.0);

        return new Rectangle2D.Double[]
        {
            NW, N, NE, E, SE, S, SW, W
        };

    }

    public void setHandleArray(double scale)
    {
        this.rectHandleArray = createRectHandle(scale);
    }

    public Rectangle2D.Double[] getHandleArray()
    {
        return this.rectHandleArray;
    }

}
