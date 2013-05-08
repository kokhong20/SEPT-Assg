/**
 *
 */
package model;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public abstract class PASVGElement implements PAAttributeConstant
{
    protected PASVGGroup parentGroup;
    protected String id;
    protected Color fill;
    protected Color stroke;
    protected double strokeWidth;
    protected boolean isGrouped;
    protected Node node;

    /**
     *
     */
    public PASVGElement()
    {
    }

    /**
     * Constructor which receives fill, stroke and strokeWidth for rectangle and
     * circle object
     *
     * @param fill
     * @param stroke
     * @param strokeWidth
     *
     */
    public PASVGElement(Color fill, Color stroke, double strokeWidth)
    {
        this.fill = fill;
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
        isGrouped = false;
        parentGroup = null;
    }

    /**
     * Constructor which receives stoke and strokeWidth for line object
     *
     * @param stroke
     * @param strokeWidth
     */
    public PASVGElement(Color stroke, double strokeWidth)
    {
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Constructor which receives a node
     *
     * @param node
     */
    public PASVGElement(Node node)
    {
        // TODO Auto-generated constructor stub
        Element element = (Element) node;
        this.fill = (PAColor.setColor(element.getAttribute("fill"), FILL));
        this.stroke = (PAColor.setColor(element.getAttribute("stroke"), STROKE));
        this.strokeWidth = (PAUnit.setUnit(element.getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH));
        this.node = node;
        this.isGrouped = false;
        this.parentGroup = null;
        this.id = (element.hasAttribute("id") ? element.getAttribute("id") : "");
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
    public PASVGElement(Node node, Color groupFill, Color groupStroke,
            double groupWidth, PASVGGroup parentGroup)
    {
        // TODO Auto-generated constructor stub
        Element element = (Element) node;
        this.fill = (element.hasAttribute("fill")
                ? PAColor.setColor(element.getAttribute("fill"), FILL) : groupFill);
        this.stroke = (element.hasAttribute("stroke")
                ? PAColor.setColor(element.getAttribute("stroke"), STROKE) : groupStroke);
        this.strokeWidth = (element.hasAttribute("stroke-width")
                ? PAUnit.setUnit(element.getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH) : groupWidth);
        this.node = node;
        this.isGrouped = true;
        this.parentGroup = parentGroup;
        this.id = (element.hasAttribute("id") ? ((Element) node).getAttribute("id") : "");
    }

    /**
     * To read attribute values from SVG elements to setters
     */
    public abstract void readAttributes();

    /**
     * @return the parentGroup
     */
    public PASVGGroup getParentGroup()
    {
        return parentGroup;
    }

    /**
     * @param parentGroup the parentGroup to set
     */
    public void setParentGroup(PASVGGroup parentGroup)
    {
        this.parentGroup = parentGroup;
    }

    /**
     * @return the id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    private void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return the fill
     */
    public Color getFill()
    {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(Color fill)
    {
        this.fill = fill;
    }

    /**
     * @return the stroke
     */
    public Color getStroke()
    {
        return stroke;
    }

    /**
     * @param stroke the stroke to set
     */
    public void setStroke(Color stroke)
    {
        this.stroke = stroke;
    }

    /**
     * @return the strokeWidth
     */
    public double getStrokeWidth()
    {
        return strokeWidth;
    }

    /**
     * @param strokeWidth the strokeWidth to set
     */
    public void setStrokeWidth(double strokeWidth)
    {
        this.strokeWidth = strokeWidth;
    }

    /**
     * @return the node
     */
    public Node getNode()
    {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Node node)
    {
        this.node = node;
    }

    /**
     * @return the isGrouped
     */
    public boolean isGrouped()
    {
        return isGrouped;
    }

    /**
     * @param isGrouped the isGrouped to set
     */
    public void setGrouped(boolean isGrouped)
    {
        this.isGrouped = isGrouped;
    }

}
