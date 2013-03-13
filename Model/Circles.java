package Model;

import org.w3c.dom.Node;

public class Circles extends Shapes
{

	private double cx;
	private double cy;
	private double r;
	
	public Circles(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}
	
	public void setCX(double cx)
	{
		this.cx = cx;
	}

	public void setCY(double cy)
	{
		this.cy = cy;
	}

	public void setR(double r)
	{
		this.r = r;
	}
	
	public double getCX()
	{
		return cx;
	}
	
	public double getCY()
	{
		return cy;
	}
	
	public double getR()
	{
		return r;
	}
	
	
	

}