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
public abstract class PASVGElement implements PAAttributeConstant  {
	private PASVGGroup parentGroup;
	private String id;
	private Color fill;
	private Color stroke;
	private double strokeWidth;
	private boolean isGrouped;
	private Node node;

	/**
	 * 
	 */
	public PASVGElement() 
	{

	}
	
	/**
	 * Constructor which receives fill, stroke and strokeWidth for rectangle and circle object
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
	}
        
        /**
         * Constructor which receives stoke and strokeWidth for line object
         * @param stroke
         * @param strokeWidth 
         */
        public PASVGElement(Color stroke,double strokeWidth)
        {
            this.stroke = stroke;
            this.strokeWidth = strokeWidth;
        }

	/**
	 * Constructor which receives a node
	 * 
	 * @param node
	 */
	public PASVGElement(Node node) {
		// TODO Auto-generated constructor stub

		this.setFill(PAColor.setColor(((Element) node).getAttribute("fill"),
				FILL));
		this.setStroke(PAColor.setColor(
				((Element) node).getAttribute("stroke"), STROKE));
		this.setStrokeWidth(PAUnit.setUnit(
				((Element) node).getAttribute("stroke-width"),
				DEFAULT_STROKE_WIDTH));
		this.setNode(node);
		this.setGrouped(false);
		this.setParentGroup(null);
		this.setId(((Element)node).hasAttribute("id") ? ((Element)node).getAttribute("id") : "");
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
			double groupWidth, PASVGGroup parentGroup) {
		// TODO Auto-generated constructor stub

		this.setFill(((Element) node).hasAttribute("fill") ? PAColor.setColor(
				((Element) node).getAttribute("fill"), FILL) : groupFill);
		this.setStroke(((Element) node).hasAttribute("stroke") ? PAColor
				.setColor(((Element) node).getAttribute("stroke"), STROKE)
				: groupStroke);
		this.setStrokeWidth(((Element) node).hasAttribute("stroke-width") ? PAUnit
				.setUnit(((Element) node).getAttribute("stroke-width"),
						DEFAULT_STROKE_WIDTH) : groupWidth);
		this.setNode(node);
		this.setGrouped(true);
		this.setParentGroup(parentGroup);
		this.setId(((Element)node).hasAttribute("id") ? ((Element)node).getAttribute("id") : "");
	}

	/**
	 * To read attribute values from SVG elements to setters
	 */
	public abstract void readAttributes();

	/**
	 * @return the parentGroup
	 */
	public PASVGGroup getParentGroup() {
		return parentGroup;
	}

	/**
	 * @param parentGroup the parentGroup to set
	 */
	public void setParentGroup(PASVGGroup parentGroup) {
		this.parentGroup = parentGroup;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fill
	 */
	public Color getFill() {
		return fill;
	}

	/**
	 * @param fill
	 *            the fill to set
	 */
	public void setFill(Color fill) {
		this.fill = fill;
	}

	/**
	 * @return the stroke
	 */
	public Color getStroke() {
		return stroke;
	}

	/**
	 * @param stroke
	 *            the stroke to set
	 */
	public void setStroke(Color stroke) {
		this.stroke = stroke;
	}

	/**
	 * @return the strokeWidth
	 */
	public double getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * @param strokeWidth
	 *            the strokeWidth to set
	 */
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	/**
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the isGrouped
	 */
	public boolean isGrouped() {
		return isGrouped;
	}

	/**
	 * @param isGrouped
	 *            the isGrouped to set
	 */
	public void setGrouped(boolean isGrouped) {
		this.isGrouped = isGrouped;
	}

}
