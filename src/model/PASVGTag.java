package model;

import java.awt.Color;
import java.util.regex.Pattern;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * 
 * @author LaiSaiHoo
 *
 */

public class PASVGTag 
{
	private double width;
	private double height;
	private double initX;
	private double initY;
	private double viewHeight;
	private double viewWidth;
	private Color stroke;
	private Color fill;
	private double strokeWidth;
	private Node node;

	/**
	 * Constructor which receives a node
	 * @param node
	 */
	public PASVGTag(Node node)
	{
		this.setNode(node);
	}
	
	/**
	 * Set values to variables to current instance if an Element Node
	 */
	public void setSVGAttr()
	{
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			if(eNode.hasAttribute("width"))
			{
				this.setWidth(PAUnit.setUnit(eNode.getAttribute("width"),false));
			}
			else
			{
				this.setWidth(500);
			}
				
			if(eNode.hasAttribute("height"))
			{
				this.setHeight(PAUnit.setUnit(eNode.getAttribute("height"),false));
			}
			else
			{
				this.setHeight(500);
			}
			
			if (eNode.hasAttribute("viewBox"))
			{
				setViewbox(eNode.getAttribute("viewBox"));
			}

			this.setStrokeWidth(PAUnit.setUnit(eNode.getAttribute("stroke-width"), true));
			this.setStroke(PAColor.setColor(eNode.getAttribute("stroke"), true));
			this.setFill(PAColor.setColor(eNode.getAttribute("fill"), false));
		}
	}
	
	/**
	 * Read in value as value of svg viewbox attribute
	 * set it to current instance variables
	 * @param value
	 */
	public void setViewbox(String value)
	{
		if (Pattern.matches("\\d+ *,* *\\d+ *,* *\\d+ *,* *\\d+", value))
		{
			value = value.replace(",", ";");
			value = value.replace(" ", ";");
			String values[]  = value.split(";");
			String attValues[] = new String[4];
			int j = 0;
			for(int i = 0; i < values.length; i++)
				if(!values[i].isEmpty())
					attValues[j++] = values[i];
			
			setInitX(PAUnit.setUnit(attValues[0],false));
			setInitY(PAUnit.setUnit(attValues[1], false));
			setViewWidth(PAUnit.setUnit(attValues[2], false));
			setViewHeight(PAUnit.setUnit(attValues[3], false));
		}
	}

	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * @return the initX
	 */
	public double getInitX() {
		return initX;
	}

	/**
	 * @param initX the initX to set
	 */
	public void setInitX(double initX) {
		this.initX = initX;
	}

	/**
	 * @return the initY
	 */
	public double getInitY() {
		return initY;
	}

	/**
	 * @param initY the initY to set
	 */
	public void setInitY(double initY) {
		this.initY = initY;
	}

	/**
	 * @return the viewHeight
	 */
	public double getViewHeight() {
		return viewHeight;
	}

	/**
	 * @param viewHeight the viewHeight to set
	 */
	public void setViewHeight(double viewHeight) {
		this.viewHeight = viewHeight;
	}

	/**
	 * @return the viewWidth
	 */
	public double getViewWidth() {
		return viewWidth;
	}

	/**
	 * @param viewWidth the viewWidth to set
	 */
	public void setViewWidth(double viewWidth) {
		this.viewWidth = viewWidth;
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
	 * @return the strokeWidth
	 */
	public double getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * @param strokeWidth the strokeWidth to set
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
	 * @param node the node to set
	 */
	public void setNode(Node node) {
		this.node = node;
	}

}
