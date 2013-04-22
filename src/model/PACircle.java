package model;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PACircle extends PAShape implements PAFillable {

	private Color fill;
	private double cx;
	private double cy;
	private double r;

	public PACircle()
	{
		
	}
	
	/**
	 * Constructor which receives a node
	 * 
	 * @param node
	 */
	public PACircle(Node node) 
	{
		super(node);

		this.setFill(PAColor.setColor(((Element)node).getAttribute("fill"), FILL));
	}

	public PACircle(Node node, Color groupFill, Color groupStroke, double groupWidth) 
	{
		super(node, groupStroke, groupWidth);

		this.setFill(((Element)node).hasAttribute("fill") ? groupFill : PAColor.setColor(((Element)node).getAttribute("fill"), FILL));
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

			this.setFill(PAColor.setColor(eNode.getAttribute("fill"), FILL));
			this.setCx(PAUnit.setUnit(eNode.getAttribute("cx"), DEFAULT_LENGTH));
			this.setCy(PAUnit.setUnit(eNode.getAttribute("cy"), DEFAULT_LENGTH));
			this.setR(PAUnit.setUnit(eNode.getAttribute("r"), DEFAULT_LENGTH));
		}
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
	 * @return the cx
	 */
	public double getCx() {
		return cx;
	}

	/**
	 * @param cx the cx to set
	 */
	public void setCx(double cx) {
		this.cx = cx;
	}

	/**
	 * @return the cy
	 */
	public double getCy() {
		return cy;
	}

	/**
	 * @param cy the cy to set
	 */
	public void setCy(double cy) {
		this.cy = cy;
	}

	/**
	 * @return the r
	 */
	public double getR() {
		return r;
	}

	/**
	 * @param r the r to set
	 */
	public void setR(double r) {
		this.r = r;
	}
}