package Model;

import org.w3c.dom.Node;

public class Rectangle extends Shapes
{
	private double x;
	private double y;
	private double width;
	private double height;

	public Rectangle(Node node) 
	{
		super(node);
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
		
	}
}
