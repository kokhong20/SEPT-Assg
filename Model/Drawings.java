package Model;
import java.awt.*;

public abstract class Drawings {

	private Color stroke;
	private double strokeWidth;
	
	public Drawings(Color colorStroke, double width) {
		// TODO Auto-generated constructor stub
		
		this.stroke = colorStroke;
		this.strokeWidth = width;
	}
	
	public void setStrokeColor(Color color)
	{
		stroke = color;
	}
	
	public Color getStrokeColor()
	{
		return stroke;
	}

	public void setStrokeWidth(double width)
	{
		strokeWidth = width;
	}
	
	public double getStrokeWidth()
	{
		return strokeWidth;
	}
	
}
