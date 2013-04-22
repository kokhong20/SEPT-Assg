package model;

import java.awt.Color;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * @author bryantylai
 *
 */
public class PALine extends PASVGElement {

	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	public PALine()
	{
		
	}
	
	/**
	 * Constructor which receives a node
	 * 
	 * @param node
	 */
	public PALine(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}

	public PALine(Node node, Color groupStroke, double groupWidth)
	{
		// TODO Auto-generated constructor stub
		super(node, null, groupStroke, groupWidth);
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
			
			this.setX1(PAUnit.setUnit(eNode.getAttribute("x1"),DEFAULT_LENGTH));
			this.setX2(PAUnit.setUnit(eNode.getAttribute("x2"),DEFAULT_LENGTH));
			this.setY1(PAUnit.setUnit(eNode.getAttribute("y1"),DEFAULT_LENGTH));
			this.setY2(PAUnit.setUnit(eNode.getAttribute("y2"),DEFAULT_LENGTH));
		}
	}
	
	@Override
	public void setFill(Color c)
	{
		this.setFill(null);
	}

	@Override
	public Color getFill()
	{
		return null;
	}

	/**
	 * @return the x1
	 */
	public double getX1() {
		return x1;
	}

	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(double x1) {
		this.x1 = x1;
	}

	/**
	 * @return the x2
	 */
	public double getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(double x2) {
		this.x2 = x2;
	}

	/**
	 * @return the y1
	 */
	public double getY1() {
		return y1;
	}

	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(double y1) {
		this.y1 = y1;
	}

	/**
	 * @return the y2
	 */
	public double getY2() {
		return y2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(double y2) {
		this.y2 = y2;
	}
}
