import java.awt.*;

public abstract class Shapes extends Drawings{

	private Color fill;
	
	public Shapes(Color color, double width, Color fillColor) {
		// TODO Auto-generated constructor stub
		super(color, width);
		this.fill = fillColor;
	}
	
	private void SetFill(Color fillColor)
	{
		this.fill = fillColor;
	}

	public Color GetFill()
	{
		return this.fill;
	}
}
