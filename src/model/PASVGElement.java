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
	private Color fill;
	private Color stroke;
	private double strokeWidth;
	private boolean isGrouped;
	private Node node;
	
	public PASVGElement()
	{
		
	}
	
	/**
	 * 
	 */
	public PASVGElement(Node node)
	{
		// TODO Auto-generated constructor stub

		this.setFill(PAColor.setColor(((Element)node).getAttribute("fill"), FILL));
		this.setStroke(PAColor.setColor(((Element)node).getAttribute("stroke"), STROKE));
		this.setStrokeWidth(PAUnit.setUnit(((Element)node).getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH));
		this.setNode(node);
	}

	public PASVGElement(Node node, Color groupFill, Color groupStroke, double groupWidth)
	{
		// TODO Auto-generated constructor stub

		this.setFill(((Element)node).hasAttribute("fill") ? groupFill : PAColor.setColor(((Element)node).getAttribute("fill"), FILL));
		this.setStroke(((Element)node).hasAttribute("stroke") ? groupStroke : PAColor.setColor(((Element)node).getAttribute("stroke"), STROKE));
		this.setStrokeWidth(((Element)node).hasAttribute("stroke-width") ? groupWidth : PAUnit.setUnit(((Element)node).getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH));
		this.setNode(node);
		this.setGrouped(true);
	}
	
	/**
	 * To read attribute values from SVG elements to setters
	 */
	public abstract void readAttributes();


	/**
	 * @return the fill
	 */
	public Color getFill() {
		return fill;
	}

	/**
	 * @param fill the fill to set
	 */
	public void setFill(Color fill) {
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
	public boolean isGrouped() {
		return isGrouped;
	}

	/**
	 * @param isGrouped the isGrouped to set
	 */
	public void setGrouped(boolean isGrouped) {
		this.isGrouped = isGrouped;
	}

}
