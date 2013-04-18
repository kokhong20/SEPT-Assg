package model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class PACircle extends PAFillableShape{

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
			
			this.setCx(PAUnit.setSymbol(eNode.getAttribute("cx"), OTHER_UNIT));
			this.setCy(PAUnit.setSymbol(eNode.getAttribute("cy"), OTHER_UNIT));
			this.setR(PAUnit.setSymbol(eNode.getAttribute("r"), OTHER_UNIT));
		}
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