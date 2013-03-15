package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Rectangles extends Shapes
{
	private double x;
	private double y;
	private double width;
	private double height;
	private Node node;

	public Rectangles(Node node) 
	{
		super(node);
		this.node = node;
		// TODO Auto-generated constructor stub
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
		if (this.node.getNodeType() == Node.ELEMENT_NODE)
		{
			Element element = (Element) this.node;
			
			setX(convert.checkUnit(element.getAttribute("x")));
			System.out.println("haha");
			System.out.println(getX());
			System.out.println("haha");
		}
	}
	
	
}
