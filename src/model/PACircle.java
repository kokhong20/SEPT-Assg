package model;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PACircle extends PASVGElement
{
    private double cx;
    private double cy;
    private double r;
    private Ellipse2D.Double ellipse2D;

    /**
     *
     */
    public PACircle()
    {
    }

    /**
     * Constructor to create a new PACircle object with all the attributes
     * @param fill
     * @param stroke
     * @param strokeWidth
     * @param cx
     * @param cy
     * @param r 
     */
    public PACircle(Color fill, Color stroke, double strokeWidth , double cx, double cy, double r)
    {
        super(fill,stroke,strokeWidth);
        this.cx = cx;
        this.cy = cy;
        this.r = r ;
    }

    /**
     * Constructor which receives a node
     *
     * @param node
     */
    public PACircle(Node node)
    {
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
    public PACircle(Node node, Color groupFill, Color groupStroke,
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

            this.setCx(PAUnit.setUnit(eNode.getAttribute("cx"), DEFAULT_LENGTH));
            this.setCy(PAUnit.setUnit(eNode.getAttribute("cy"), DEFAULT_LENGTH));
            this.setR(PAUnit.setUnit(eNode.getAttribute("r"), DEFAULT_LENGTH));
            setEllipse2D();
        }
    }

    /**
     * @return the cx
     */
    public double getCx()
    {
        return cx;
    }

    /**
     * @param cx the cx to set
     */
    public void setCx(double cx)
    {
        this.cx = cx;
    }

    /**
     * @return the cy
     */
    public double getCy()
    {
        return cy;
    }

    /**
     * @param cy the cy to set
     */
    public void setCy(double cy)
    {
        this.cy = cy;
    }

    /**
     * @return the r
     */
    public double getR()
    {
        return r;
    }

    /**
     * @param r the r to set
     */
    public void setR(double r)
    {
        this.r = r;
    }

	/**
	 * @return the ellipse2D
	 */
	public Ellipse2D.Double getEllipse2D()
	{
		return ellipse2D;
	}

	/**
	 * 
	 */
	public void setEllipse2D()
	{
        double x = cx - r;
        double y = cy - r;
        double diameter = r * 2;
		this.ellipse2D = new Ellipse2D.Double(x, y, diameter, diameter); 
	}

}