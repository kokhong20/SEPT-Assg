package Model;
import java.awt.*;

public abstract class Shapes extends Drawings
{

	private Color fill;
	
	public Shapes(Color colorStroke, double width, Color fillColor) {
		// TODO Auto-generated constructor stub
		super(colorStroke, width);
		this.fill = fillColor;
	}
	
	public void setFill(Color fillColor)
	{
		fill = fillColor;
	}

	public Color getFill()
	{
		return fill;
	}
}
