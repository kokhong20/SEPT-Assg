package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author bryantylai
 *
 */
public class PARectangle extends PAFillableShape {

	private double x;
	private double y;
	private double width;
	private double height;

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
	 * {@inheritDoc}
	 */
	@Override
	public void readAttributes() 
	{
		// TODO Auto-generated method stub
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			this.setX(PAUnit.setSymbol(eNode.getAttribute("x"),OTHER_UNIT));
			this.setY(PAUnit.setSymbol(eNode.getAttribute("y"),OTHER_UNIT));
			this.setWidth(PAUnit.setSymbol(eNode.getAttribute("width"),OTHER_UNIT));
			this.setHeight(PAUnit.setSymbol(eNode.getAttribute("height"),OTHER_UNIT));
		}
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
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
}
