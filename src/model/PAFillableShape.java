package model;

import java.awt.Color;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public abstract class PAFillableShape extends PAShape {
	private Color fill;
	
	/**
	 * Constructor which receives a node
	 * Call setColor method to set value 
	 * 
	 * @param node
	 */
	public PAFillableShape(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);

		this.setFill(PAColor.setColor(((Element)node).getAttribute("fill"),false));
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
}