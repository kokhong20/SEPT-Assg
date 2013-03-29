package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Rectangles extends Shapes
{
	private double x;
	private double y;
	private double width;
	private double height;

	public Rectangles() {
		// TODO Auto-generated constructor stub
	}

	public Rectangles(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}

	@Override
	public void readAttributes() 
	{
		// TODO Auto-generated method stub
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			this.setX(Units.setUnit(eNode.getAttribute("x"),false));
			this.setY(Units.setUnit(eNode.getAttribute("y"),false));
			this.setWidth(Units.setUnit(eNode.getAttribute("width"),false));
			this.setHeight(Units.setUnit(eNode.getAttribute("height"),false));
		}
	}
}
