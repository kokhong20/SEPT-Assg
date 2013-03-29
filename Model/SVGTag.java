package Model;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SVGTag 
{
	private double width;
	private double height;
	//private Color stroke;
	//private Color fill;
	//private double strokeWidth;
	
	private Node node;
	
	public SVGTag()
	{
		
	}
	
	public SVGTag(Node node)
	{
		this.node = node;
	}
	
	public void setWidth(double width)
	{
		this.width = width;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	/*public void setStrokeWidth(double strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}
	
	public double getStrokeWidth()
	{
		return this.strokeWidth;
	}*/
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	/*public Color getFill()
	{
		return this.fill;
	}
	
	public void setFill(Color color)
	{
		this.fill = color;
	}

	public Color getStroke()
	{
		return this.stroke;
	}
	
	public void setStroke(Color color)
	{
		this.stroke = color;
	}*/
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return this.node;
	}
	
	public void setSVGAttr()
	{
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			if(eNode.hasAttribute("width"))
				this.setWidth(Units.setUnit(eNode.getAttribute("width"),false));
			else
				this.setWidth(500);
				
			if(eNode.hasAttribute("height"))
				this.setHeight(Units.setUnit(eNode.getAttribute("height"),false));
			else
				this.setHeight(500);

			//this.setStrokeWidth(Units.setUnit(eNode.getAttribute("stroke-width")));
			//this.setFill(Coloring.setColor(eNode.getAttribute("fill")));
			//this.setStroke(Coloring.setColor(eNode.getAttribute("stroke")));
		}
	}

}
