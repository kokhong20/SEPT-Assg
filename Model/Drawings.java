package Model;

import java.awt.Color;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public abstract class Drawings 
{

	private Color stroke;
	private float strokeWidth;
	private Node node;
	
	// Constructor which receives a node
	// Call setColor and setUnit method to set value 
	public Drawings(Node node) 
	{
		// TODO Auto-generated constructor stub
		
		this.stroke = Coloring.setColor(((Element)node).getAttribute("stroke"));
		this.strokeWidth = (float) Units.setUnit(((Element)node).getAttribute("stroke-width"));
		this.node = node;
	}
	
	public void setStrokeColor(Color color)
	{
		stroke = color;
	}
	
	public Color getStrokeColor()
	{
		return stroke;
	}

	public void setStrokeWidth(float width)
	{
		strokeWidth = width;
	}
	
	public float getStrokeWidth()
	{
		return strokeWidth;
	}
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return this.node;
	}
	
	public abstract void readAttributes();
}
