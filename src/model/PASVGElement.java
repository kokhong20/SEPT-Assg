/**
 *
 */
package model;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 * @since 1.0
 * @version 1.1
 * <p>This class provides a skeletal implementation for all svg elements</p>
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
     * Creates an empty PASVGElement
     */
    public PASVGElement()
    {
    }

    /**
     * Creates a PASVGElement which receives fill, stroke and strokeWidth for rectangle and
     * circle object
     *
     * @param fill fill of a svg element
     * @param stroke stroke of a svg element
     * @param strokeWidth stroke width of a svg element
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
     * Creates a PASVGElement which receives stoke and strokeWidth for line object
     *
     * @param stroke stroke of a svg element
     * @param strokeWidth stroke width of a svg element
     */
    public PASVGElement(Color stroke, double strokeWidth)
    {
        this.stroke = stroke;
        this.strokeWidth = strokeWidth;
    }

    /**
     * Creates a PASVGElement based on a node received
     *
     * @param node an Element node
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
     * Creates a PASVGElement which receives a node and groupFill, groupStroke, groupWidth
     * from parent group
     *
     * @param node an Element node
     * @param groupFill fill of its group
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
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
     * 
     * Copy a PASVGElement which receives groupFill, groupStroke, groupWidth, id
     * from another group
     * 
     * @param groupFill fill of its group
     * @param groupStroke stroke of its group
     * @param groupWidth stroke width of its group
     * @param parentGroup the parent group
     * @param id the group id
     */
    public PASVGElement(Color groupFill, Color groupStroke,
            double groupWidth, PASVGGroup parentGroup, String id)
    {
        // TODO Auto-generated constructor stub
        Element element = (Element) node;
        this.fill = groupFill;
        this.stroke = groupStroke;
        this.strokeWidth = groupWidth;
        this.isGrouped = true;
        this.parentGroup = parentGroup;
        this.id = id;
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
    public void setId(String id)
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
