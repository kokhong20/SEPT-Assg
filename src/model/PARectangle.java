package model;

import java.awt.Color;

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

    /**
     *
     */
    public PARectangle()
    {
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

}
