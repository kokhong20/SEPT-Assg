package model;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public abstract class PAShape {

	private Color stroke;
	private float strokeWidth;
	private String id;
	private boolean isGrouped;
	private Node node;
	
	/**
	 * Constructor which receives a node 
	 * Call setColor and setUnit method to set value 
	 * 
	 * @param node
	 */
	public PAShape(Node node) 
	{
		// TODO Auto-generated constructor stub
		
		this.setStroke(PAColor.setColor(((Element)node).getAttribute("stroke"), true));
		this.setStrokeWidth((float) PAUnit.setUnit(((Element)node).getAttribute("stroke-width"), true));
		this.setNode(node);
	}
	
	/**
	 * To read attribute values from SVG elements to setters
	 */
	public abstract void readAttributes();

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
	 * @return the node
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * @param node the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * @return the strokeWidth
	 */
	public float getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * @param strokeWidth the strokeWidth to set
	 */
	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	/**
	 * @return the stroke
	 */
	public Color getStroke() {
		return stroke;
	}

	/**
	 * @param stroke the stroke to set
	 */
	public void setStroke(Color stroke) {
		this.stroke = stroke;
	}
}
