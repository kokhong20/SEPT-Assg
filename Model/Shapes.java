package Model;
import java.awt.*;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class Shapes extends Drawings
{

	private Color fill;
	
	public Shapes(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);

		this.fill = Coloring.setColor(((Element)node).getAttribute("fill"));
	}
	
	public void setFill(Color fill)
	{
		this.fill = fill;
	}

	public Color getFill()
	{
		return fill;
	}

}
