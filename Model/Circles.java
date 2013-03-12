package Model;

import java.awt.Color;

public class Circles extends Shapes{

	private double cx;
	private double cy;
	private double r;
	
	public Circles(Color colorStroke, double width, Color fillColor,double cx, double cy, double r) {
		// TODO Auto-generated constructor stub
		super(colorStroke,width,fillColor);
		this.cx = cx;
		this.cy = cy;
		this.r = r;
	}
	
	private void setCX(double length)
	{
		cx = length;
	}

	private void setCY(double length)
	{
		cy = length;
	}

	private void setR(double length)
	{
		r = length;
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