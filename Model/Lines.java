package Model;
import java.awt.*;

public class Lines extends Drawings{

	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	public Lines(Color colorStroke, double width, double x1, double x2, double y1, double y2) {
		// TODO Auto-generated constructor stub
		super(colorStroke, width);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	private void setX1(double x1)
	{
		this.x1 = x1;
	}

	private void setX2(double x2)
	{
		this.x2 = x2;
	}

	private void setY1(double y1)
	{
		this.y1 = y1;
	}

	private void setY2(double y2)
	{
		this.y2 = y2;
	}
	
	public double getX1()
	{
		return this.x1;
	}
	
	public double getX2()
	{
		return this.x2;
	}
	
	public double getY1()
	{
		return this.y1;
	}
	
	public double getY2()
	{
		return this.y2;
	}

}