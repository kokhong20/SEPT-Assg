import java.awt.*;

public abstract class Drawings {

	private Color stroke;
	private double strokeWidth;
	
	public Drawings(Color color, double width) {
		// TODO Auto-generated constructor stub
		
		this.stroke = color;
		this.strokeWidth = width;
	}
	
	private void SetStrokeColor(Color color)
	{
		this.stroke = color;
	}
	
	public Color GetStrokeColor()
	{
		return this.stroke;
	}

	private void SetStrokeWidth(double width)
	{
		this.strokeWidth = width;
	}
	
	public double GetStrokeWidth()
	{
		return this.strokeWidth;
	}
	
}
