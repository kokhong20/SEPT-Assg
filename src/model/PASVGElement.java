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
	private Color stroke;
	private double strokeWidth;
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

		this.setStroke(PAColor.setColor(((Element)node).getAttribute("stroke"), STROKE));
		this.setStrokeWidth(PAUnit.setUnit(((Element)node).getAttribute("stroke-width"), DEFAULT_STROKE_WIDTH));
		this.setNode(node);
	}
	
	/**
	 * To read attribute values from SVG elements to setters
	 */
	public abstract void readAttributes();

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

}
