package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
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
     *
     */
    public PARectangle()
    {
    }

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
     * Constructor which receives a node
     *
     * @param node
     */
    public PARectangle(Node node)
    {
        // TODO Auto-generated constructor stub
        super(node);
    }

    /**
     * Constructor which receives a node and groupFill, groupStroke, groupWidth
     * from parent group
     *
     * @param node
     * @param groupFill
     * @param groupStroke
     * @param groupWidth
     * @param parentGroup
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
     *
     */
    public void setRectangle2D()
    {
        rectangle2D = new Rectangle2D.Double(x, y, width, height);
    }

    public Rectangle2D.Double[] createRectHandle(double scale)
    {
        Rectangle2D r = this.rectangle2D.getBounds2D();
        double xPoint = r.getX() * scale;
        double yPoint = r.getY() * scale;
        double w = r.getWidth() * scale;
        double h = r.getHeight() * scale;
        
        Rectangle2D.Double rect1 = new Rectangle2D.Double(xPoint - 3.0, yPoint - 3.0, 6.0,
                6.0);


        Rectangle2D.Double rect2 = new Rectangle2D.Double(xPoint + w - 3.0, yPoint - 3.0,
                6.0, 6.0);


        Rectangle2D.Double rect3 = new Rectangle2D.Double(xPoint - 3.0, yPoint + h - 3.0,
                6.0, 6.0);


        Rectangle2D.Double rect4 = new Rectangle2D.Double(xPoint + w - 3.0, yPoint + h - 3.0,
                6.0, 6.0);


        Rectangle2D.Double rect5 = new Rectangle2D.Double(xPoint + (w / 2) - 3.0, yPoint - 3.0, 6.0,
                6.0);


        Rectangle2D.Double rect6 = new Rectangle2D.Double(xPoint - 3.0, yPoint + (h / 2) - 3.0,
                6.0, 6.0);


        Rectangle2D.Double rect7 = new Rectangle2D.Double(xPoint + w - 3.0, yPoint + (h / 2) - 3.0,
                6.0, 6.0);


        Rectangle2D.Double rect8 = new Rectangle2D.Double(xPoint + (w / 2) - 3.0, yPoint + h - 3.0,
                6.0, 6.0);
        
        return new Rectangle2D.Double[]{rect1,rect2,rect3,rect4,rect5,rect6,rect7,rect8};

    }
    
    public void setHandleArray(Rectangle2D.Double[] handleArray)
    {
        this.rectHandleArray = handleArray;
    }
    
    public Rectangle2D.Double[] getHandleArray()
    {
        return this.rectHandleArray;
    }
}
