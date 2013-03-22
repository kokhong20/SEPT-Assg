package Model;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Lines extends Drawings
{

	private double x1;
	private double x2;
	private double y1;
	private double y2;

	// Constructor which receives a node
	// Call setUnit method to set value 
	public Lines(Node node) 
	{
		// TODO Auto-generated constructor stub
		super(node);
	}
	
	public void setX1(double x1)
	{
		this.x1 = x1;
	}

	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	public void setY1(double y1)
	{
		this.y1 = y1;
	}

	public void setY2(double y2)
	{
		this.y2 = y2;
	}
	
	public double getX1()
	{
		return this.x1;
	}
	
	public double getX2()
	{
		return this.x2;
	}
	
	public double getY1()
	{
		return this.y1;
	}
	
	public double getY2()
	{
		return this.y2;
	}

	@Override
	public void readAttributes() 
	{
		// TODO Auto-generated method stub
		if (this.getNode().getNodeType() == Node.ELEMENT_NODE)
		{
			Element eNode = (Element) this.getNode();
			
			this.setX1(Units.setUnit(eNode.getAttribute("x1")));
			this.setX2(Units.setUnit(eNode.getAttribute("x2")));
			this.setY1(Units.setUnit(eNode.getAttribute("y1")));
			this.setY2(Units.setUnit(eNode.getAttribute("y2")));
		}
	}
}
