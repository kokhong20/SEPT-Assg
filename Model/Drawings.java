package Model;
import java.awt.*;

import org.w3c.dom.Node;

public abstract class Drawings 
{

	private Color stroke;
	private double strokeWidth;
	private Node node;
	
	public Drawings(Node node) {
		// TODO Auto-generated constructor stub
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
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return node;
	}
	
}
