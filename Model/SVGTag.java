package Model;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SVGTag 
{
	private double width;
	private double height;
	private Node node;
	
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
	
	public void setHeight(double height)
	{
		this.height = height;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public Node getNode()
	{
		return this.node;
	}
	
	public void setSVGWidthAndHeight()
	{
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			this.setWidth(Units.setUnit(eNode.getAttribute("width")));
			this.setHeight(Units.setUnit(eNode.getAttribute("height")));
			System.out.println("inside svgggg");
		}
	}

}
