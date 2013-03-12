import java.awt.*;

public class Lines extends Drawings{

	private double x1;
	private double x2;
	private double y1;
	private double y2;
	
	public Lines(Color color, double width, double x1, double x2, double y1, double y2) {
		// TODO Auto-generated constructor stub
		super(color, width);
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	private void SetX1(double x1)
	{
		this.x1 = x1;
	}

	private void SetX2(double x2)
	{
		this.x2 = x2;
	}

	private void SetY1(double y1)
	{
		this.y1 = y1;
	}

	private void SetY2(double y2)
	{
		this.y2 = y2;
	}
	
	public double GetX1()
	{
		return this.x1;
	}
	
	public double GetX2()
	{
		return this.x2;
	}
	
	public double GetY1()
	{
		return this.y1;
	}
	
	public double GetY2()
	{
		return this.y2;
	}

}
